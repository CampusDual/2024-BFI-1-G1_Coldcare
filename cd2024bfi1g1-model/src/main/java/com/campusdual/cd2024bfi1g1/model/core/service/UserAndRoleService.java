package com.campusdual.cd2024bfi1g1.model.core.service;

import java.util.*;

import com.campusdual.cd2024bfi1g1.model.core.dao.*;
import com.campusdual.cd2024bfi1g1.model.core.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusdual.cd2024bfi1g1.api.core.service.IUserAndRoleService;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.security.PermissionsProviderSecured;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.common.util.remote.BytesBlock;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.jee.server.security.SecurityTools;
import com.ontimize.jee.server.security.encrypt.IPasswordEncryptHelper;

import java.security.SecureRandom;

@Lazy
@Service("UserAndRoleService")
public class UserAndRoleService implements IUserAndRoleService {

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;
    /**
     * The user roles dao.
     */
    @Autowired
    private UserRoleDao userRolesDao;
    /**
     * The user dao.
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * The server role dao.
     */
    @Autowired
    private RoleServerPermissionDao roleServerPermissionDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired(required = false)
    private IPasswordEncryptHelper passwordEncrypter;

    private Util util;

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult userQuery(final Map<?, ?> keysValues, final List<?> attributes) throws OntimizeJEERuntimeException {

        if (!attributes.contains("CMP_NAME")) {
            ((List<String>) attributes).add("CMP_NAME");
        }

        final EntityResult toRet = this.daoHelper.query(this.userDao, keysValues, attributes);
        if (toRet.containsKey(UserDao.PHOTO)) {
            final List<Object> photoCustomer = (List<Object>) toRet.get(UserDao.PHOTO);
            for (int i = 0; i < photoCustomer.size(); i++) {
                final Object o = photoCustomer.get(i);
                if (o instanceof BytesBlock) {
                    photoCustomer.set(i, ((BytesBlock) o).getBytes());
                }
            }
        }

        return toRet;
    }

    @Override
    public EntityResult searchQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.userDao, keyMap, attrList, "search");
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public AdvancedEntityResult userPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.userDao, keysValues, attributes, recordNumber, startIndex, orderBy);
    }


    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult userUpdate(final Map<?, ?> attributesValues, final Map<?, ?> keysValues) throws OntimizeJEERuntimeException {
        EntityResult uroUpdateRes = null;
        try {
            if (attributesValues.containsKey(UserRoleDao.ROL_ID)) {
                uroUpdateRes = this.daoHelper.update(this.userRolesDao, this.encryptPassword(attributesValues), keysValues);
                attributesValues.remove(UserRoleDao.ROL_ID);
            }
            if (!attributesValues.isEmpty()) {
                return this.daoHelper.update(this.userDao, this.encryptPassword(attributesValues), keysValues);
            } else if (uroUpdateRes != null) {
                return uroUpdateRes;
            } else {
                EntityResult res = new EntityResultMapImpl();
                res.setCode(EntityResult.OPERATION_WRONG);
                res.setMessage("USER_UPDATE_ERROR");
                return res;
            }

        } finally {
            this.invalidateSecurityManager();
        }
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult userDelete(final Map<?, ?> keysValues) throws OntimizeJEERuntimeException {
        try {
            return this.daoHelper.delete(this.userDao, keysValues);
        } finally {
            this.invalidateSecurityManager();
        }
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult userInsert(final Map<String, Object> keysValues) throws OntimizeJEERuntimeException {


        String password = generateRandomPassword();

        Map<String, Object> updatedKeysValues = new HashMap<>();

        for (Map.Entry<?, ?> entry : keysValues.entrySet()) {
            updatedKeysValues.put((String) entry.getKey(), entry.getValue());
        }
        updatedKeysValues.put(UserDao.PASSWORD, password);
        updatedKeysValues.put(UserDao.PASSWORD, encryptPassword(password));
        EntityResult userInsertResult = this.daoHelper.insert(this.userDao, updatedKeysValues);

        if (!userInsertResult.isEmpty()) {
            //String email = keysValues.get(UserDao.EMAIL).toString();
            sendEmail("torresrdriguezoscar@gmail.com", "", password);
            Integer userId = (Integer) userInsertResult.get(UserDao.USR_ID);
            Integer rolId = (Integer) keysValues.get(RoleDao.ROL_ID);

            Map<String, Object> roleKeysValues = new HashMap<>();
            roleKeysValues.put(UserRoleDao.USR_ID, userId);
            roleKeysValues.put(UserRoleDao.ROL_ID, rolId);

            return this.daoHelper.insert(this.userRolesDao, roleKeysValues);

        } else {
            throw new OntimizeJEERuntimeException("No se pudo insertar el usuario.");
        }
    }

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("coldcare33@gmail.com"); // Asegúrate de que el correo coincide con el username del YML
        mailSender.send(message);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult roleQuery(final Map<?, ?> keysValues, final List<?> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.roleDao, keysValues, attributes);
    }

    @Override
    public EntityResult roleWithoutAdminQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.roleDao, keysValues, attributes, "asignRole");
    }

    @Override
    public EntityResult myRoleQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityResult e = new EntityResultMapImpl();
        Map<String, String> map = new HashMap<>();
        String role = authentication.getAuthorities().toArray()[0].toString();

        map.put(RoleDao.ROL_NAME, role);

        e.addRecord(map);

        return this.daoHelper.query(this.roleDao, map, attributes);
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public AdvancedEntityResult rolePaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.roleDao, keysValues, attributes, recordNumber, startIndex, orderBy);
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult roleUpdate(final Map<?, ?> attributesValues, final Map<?, ?> keysValues) throws OntimizeJEERuntimeException {
        try {
            return this.daoHelper.update(this.roleDao, attributesValues, keysValues);
        } finally {
            this.invalidateSecurityManager();
        }
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult roleDelete(final Map<?, ?> keysValues) throws OntimizeJEERuntimeException {
        try {
            this.roleServerPermissionDao.unsafeDelete(keysValues);
            return this.daoHelper.delete(this.roleDao, keysValues);
        } finally {
            this.invalidateSecurityManager();
        }
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult roleInsert(final Map<?, ?> keysValues) throws OntimizeJEERuntimeException {
        try {
            return this.daoHelper.insert(this.roleDao, keysValues);
        } finally {
            this.invalidateSecurityManager();
        }
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult serverRoleQuery(final Map<?, ?> keysValues, final List<?> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.roleServerPermissionDao, keysValues, attributes, "fullRolesWithServerPermissions");
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public AdvancedEntityResult serverRolePaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.roleServerPermissionDao, keysValues, attributes, recordNumber, startIndex, orderBy, "fullRolesWithServerPermissions");
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult serverRoleUpdate(final Map<?, ?> attributesValues, final Map<?, ?> keysValues) throws OntimizeJEERuntimeException {
        try {
            if ("S".equals(attributesValues.get(RoleServerPermissionDao.ACTIVED))) {
                // insert
                final Map<String, Object> valuesToInsert = new HashMap<>();
                valuesToInsert.put(RoleServerPermissionDao.ROL_ID, keysValues.get(RoleServerPermissionDao.ROL_ID));
                valuesToInsert.put(RoleServerPermissionDao.SRP_ID, keysValues.get(RoleServerPermissionDao.SRP_ID));
                return this.daoHelper.insert(this.roleServerPermissionDao, valuesToInsert);
            } else if (keysValues.get(RoleServerPermissionDao.RSP_ID) != null) {
                // delete
                final Map<String, Object> valuesToDelete = new HashMap<>();
                valuesToDelete.put(RoleServerPermissionDao.RSP_ID, keysValues.get(RoleServerPermissionDao.RSP_ID));
                return this.daoHelper.delete(this.roleServerPermissionDao, valuesToDelete);
            }
            return new EntityResultMapImpl();
        } finally {
            this.invalidateSecurityManager();
        }
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult rolesForUserQuery(final Map<?, ?> keysValues, final List<?> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.userRolesDao, keysValues, attributes, "fullRolesWithUser");
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public AdvancedEntityResult rolesForUserPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.userRolesDao, keysValues, attributes, recordNumber, startIndex, orderBy, "fullRolesWithUser");
    }

    /*
     * (non-Javadoc)
     */

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult rolesForUserUpdate(final Map<?, ?> attributesValues, final Map<?, ?> keysValues)
            throws OntimizeJEERuntimeException {
        try {
            if ("S".equals(attributesValues.get(UserRoleDao.ACTIVED))) {
                // insert
                final Map<String, Object> valuesToInsert = new HashMap<>();
                valuesToInsert.put(UserRoleDao.USR_ID, keysValues.get(UserRoleDao.USR_ID));
                valuesToInsert.put(UserRoleDao.ROL_ID, keysValues.get(UserRoleDao.ROL_ID));
                return this.daoHelper.insert(this.userRolesDao, valuesToInsert);
            } else if (keysValues.get(UserRoleDao.URO_ID) != null) {
                // delete
                final Map<String, Object> valuesToDelete = new HashMap<>();
                valuesToDelete.put(UserRoleDao.URO_ID, keysValues.get(UserRoleDao.URO_ID));
                return this.daoHelper.delete(this.userRolesDao, valuesToDelete);
            }
            return new EntityResultMapImpl();
        } finally {
            this.invalidateSecurityManager();
        }
    }

    @Override
    public String encryptPassword(final String password) throws OntimizeJEERuntimeException {
        if (this.passwordEncrypter != null) {
            return this.passwordEncrypter.encrypt(password);
        }
        return password;
    }

    /**
     * Refresh security manager.
     */
    private void invalidateSecurityManager() {
        SecurityTools.invalidateSecurityManager(this.daoHelper.getApplicationContext());
    }

    protected Map<String, Object> encryptPassword(final Map<?, ?> av) {
        final Map<String, Object> map = (Map<String, Object>) av;

        if (this.passwordEncrypter != null) {
            final String pass = (String) map.computeIfPresent(UserDao.PASSWORD,
                    (key, value) -> value == null ? null : this.passwordEncrypter.encrypt((String) value));
            if (pass != null) {
                map.put(UserDao.PASSWORD, pass);
            }
        }
        return map;
    }

    @Override
    public EntityResult passwordUpdate(final Map<?, ?> attributesValues, final Map<?, ?> keysValues)
            throws OntimizeJEERuntimeException {
        final UserInformation userInfo = (UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final Map<String, Object> kvq = new HashMap<>();
        kvq.put(UserDao.LOGIN, userInfo.getUsername());
        final EntityResult oldPassword = this.daoHelper.query(userDao, kvq, List.of(UserDao.USR_ID, UserDao.PASSWORD));
        final Map<String, Object> r = oldPassword.getRecordValues(0);
        if (this.checkPasswords((String) r.get(UserDao.PASSWORD), (String) attributesValues.get(UserDao.OLD_PASSWORD))) {
            final Map<String, Object> newPassword = new HashMap<>();
            newPassword.put(UserDao.PASSWORD, this.encryptPassword((String) attributesValues.get(UserDao.NEW_PASSWORD)));
            final Map<String, Object> kvu = new HashMap<>();
            kvu.put(UserDao.USR_ID, r.get(UserDao.USR_ID));
            return this.daoHelper.update(userDao, newPassword, kvu);
        } else {
            final EntityResult error = new EntityResultMapImpl();
            error.setCode(EntityResult.OPERATION_WRONG);
            error.setMessage("The old password isn't correct!");
            return error;
        }
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public EntityResult profileQuery(final Map<?, ?> keysValues, final List<?> attributes) throws OntimizeJEERuntimeException {
        final UserInformation userInfo = (UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final Map<String, Object> kv = new HashMap<>();
        kv.put(UserDao.LOGIN, userInfo.getUsername());
        final EntityResult toRet = this.daoHelper.query(this.userDao, kv, attributes);
        if (toRet.containsKey(UserDao.PHOTO)) {
            final List<Object> photoCustomer = (List<Object>) toRet.get(UserDao.PHOTO);
            for (int i = 0; i < photoCustomer.size(); i++) {
                final Object o = photoCustomer.get(i);
                if (o instanceof BytesBlock) {
                    photoCustomer.set(i, ((BytesBlock) o).getBytes());
                }
            }
        }

        return toRet;
    }

    /*
     * (non-Javadoc)
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult profileUpdate(final Map<?, ?> attributesValues, final Map<?, ?> keysValues) throws OntimizeJEERuntimeException {
        try {
            final UserInformation userInfo = (UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            final Map<String, Object> kvq = new HashMap<>();
            kvq.put(UserDao.LOGIN, userInfo.getUsername());
            final EntityResult er = this.daoHelper.query(this.userDao, kvq, List.of(UserDao.USR_ID));
            final Map<String, Object> kvu = new HashMap<>();
            kvu.put(UserDao.USR_ID, er.getRecordValues(0).get(UserDao.USR_ID));
            return this.daoHelper.update(this.userDao, this.encryptPassword(attributesValues), kvu);
        } finally {
            this.invalidateSecurityManager();
        }
    }

    @Override
    public EntityResult loginUserQuery(final Map<?, ?> keysValues, final List<?> attributes) throws OntimizeJEERuntimeException {
        final UserInformation userInfo = (UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final EntityResult eR = new EntityResultMapImpl();
        final Map<String, Object> usrMap = new HashMap<>();

        for (final Object key : userInfo.getOtherData().keySet()) {
            if (userInfo.getOtherData().get(key) != null) {
                usrMap.put(String.valueOf(key), userInfo.getOtherData().get(key));
            }
        }
        eR.putAll(usrMap);
        return eR;
    }

    @Override
    public EntityResult getClientPermissions() {
        final Collection<GrantedAuthority> authorities = ((UserInformation) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getAuthorities();
        if (authorities.isEmpty()) {
            return new EntityResultMapImpl();
        } else {
            final String userRole = authorities.iterator().next().getAuthority();
            return this.daoHelper.query(this.roleDao, new HashMap<>(Map.of(RoleDao.ROL_NAME, userRole)),
                    List.of(RoleDao.JSON_CLIENT_PERMISSION));
        }
    }


    protected boolean checkPasswords(final String storedPassword, final String password) throws OntimizeJEERuntimeException {
        if (this.passwordEncrypter == null) {
            return (password != null && storedPassword.equals(password));
        } else {
            try {
                this.passwordEncrypter.checkPasswords(storedPassword, password);
                return true;
            } catch (final Exception e) {
                return false;
            }
        }
    }

    public static Integer getUserCompanyId(DefaultOntimizeDaoHelper daoHelper, UserDao userDao) {
        Integer userId = Util.getUserId();

        Map<String, Object> filter = new HashMap<>();
        filter.put(UserDao.USR_ID, userId);
        List<String> columns = List.of(UserDao.CMP_ID);

        EntityResult userEr = daoHelper.query(userDao, filter, columns);
        if (userEr.isEmpty()) {
            throw new RuntimeException("Unknown user");
        }

        Map<String, Object> user = userEr.getRecordValues(0);
        return (Integer) user.get(UserDao.CMP_ID);
    }


    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
    private static final int PASSWORD_LENGTH = 12;

    public static String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }

}