import { MenuRootItem } from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'devices', name: 'DEVICES', icon: 'router', route: '/main/devices' },
  { id: 'contenedores', name: 'CONTAINERS', icon: 'local_shipping', route: '/main/containers' },
  { id: 'lots', name: 'LOTS', icon: 'archive', route: '/main/lots' },
  {
    id: 'admin', name: 'ADMIN', tooltip: 'ADMIN', icon: 'admin_panel_settings',
    items: [
      // { id: 'roles', name: 'ROLES', tooltip: 'ROLES', route: '/main/admin/roles', icon: 'supervisor_account' },
      { id: 'users', name: 'USERS', tooltip: 'USERS', route: '/main/admin/users', icon: 'person' },
      { id: 'companies', name: 'COMPANIES', tooltip: 'COMPANIES', icon: 'apartment', route: '/main/admin/companies' },
      { id: 'devices-without-users', name: 'DEVICE_ASSIGNMENT', tooltip: 'DevicesWithoutUsers', route: '/main/admin/devices-without-users', icon: 'developer_board' },
      { id: 'medidas', name: 'MEASUREMENTS', tooltip: 'MEASUREMENTS', icon: 'thermostat', route: '/main/admin/medidas' },
    ]
  },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'power_settings_new', confirm: 'yes' }
];
