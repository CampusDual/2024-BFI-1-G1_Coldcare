import { MenuRootItem } from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'devices', name: 'DEVICES', icon: 'router', route: '/main/devices' },
  { id: 'contenedores', name: 'CONTAINERS', icon: 'local_shipping', route: '/main/containers' },
  {
    id: 'admin', name: 'ADMIN', tooltip: 'ADMIN', icon: 'admin_panel_settings',
    items: [
      // { id: 'roles', name: 'ROLES', tooltip: 'ROLES', route: '/main/admin/roles', icon: 'supervisor_account' },
      // { id: 'users', name: 'USERS', tooltip: 'USERS', route: '/main/admin/users', icon: 'person' },
      { id: 'devices-without-users', name: 'DEVICE_ASSIGNMENT', tooltip: 'DevicesWithoutUsers', route: '/main/admin/devices-without-users', icon: 'developer_board' },
      { id: 'medidas', name: 'MEASUREMENTS', icon: 'thermostat', route: '/main/admin/medidas' },
    ]
  },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'power_settings_new', confirm: 'yes' }
];
