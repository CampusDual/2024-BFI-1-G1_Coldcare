import { MenuRootItem } from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'devices', name: 'DEVICES', icon: 'router', route: '/main/devices' },
  { id: 'contenedores', name: 'CONTAINERS', icon: 'takeout_dining', route: '/main/containers' },
  { id: 'lots', name: 'LOTS', icon: 'archive', route: '/main/lots' },
  { id: 'locations', name: 'LOCATIONS', icon: 'location_on', route: '/main/locations' },
  { id: 'vehicles', name: 'VEHICLES', tooltip: 'VEHICLES', icon: 'directions_car', route: '/main/vehicles' },
  { id: 'transports', name: 'TRANSPORTS', tooltip: 'TRANSPORTS', icon:'local_shipping', route: '/main/transports' },
  {
    id: 'masters', name: 'MASTERS', icon: 'key',
    items: [
      { id: 'locations', name: 'LOCATIONS', icon: 'location_on', route: '/main/locations' },
      { id: 'vehicles', name: 'VEHICLES', icon: 'directions_car', route: '/main/vehicles' },
      { id: 'products', name: 'PRODUCTS', icon: 'inventory', route: '/main/products' }
    ]
  },
  { id: 'devices-without-users', name: 'DEVICE_ASSIGNMENT', route: '/main/admin/devices-without-users', icon: 'developer_board' },
  { id: 'medidas', name: 'MEASUREMENTS', icon: 'thermostat', route: '/main/admin/medidas' },
  { id: 'companies', name: 'COMPANIES', icon: 'apartment', route: '/main/admin/companies' },
  { id: 'consumptions', name: 'USAGE', icon: 'payments', route: '/main/admin/bills' },
  { id: 'plans', name: 'PLANS', icon: 'paid', route: '/main/admin/plans' },
  { id: 'users', name: 'USERS', route: '/main/admin/users', icon: 'person' },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'power_settings_new', confirm: 'yes' }
];
