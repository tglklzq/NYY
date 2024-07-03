import {
    UserOutlined, BellOutlined, MailOutlined, SettingOutlined
} from '@ant-design/icons-vue';

export function getMenuIcon(menuKey) {
    switch (menuKey) {
        case 'Menu1':
            return UserOutlined;
        case 'Menu2':
            return BellOutlined;
        case 'Menu3':
            return MailOutlined;
        case 'Menu4':
            return SettingOutlined;
        default:
            return UserOutlined; // 或者返回一个默认图标
    }
}
