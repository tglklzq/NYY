export function generateRoutesFromMenu(menuData) {
    const routes = [];

    Object.keys(menuData).forEach(menuKey => {
        menuData[menuKey].forEach(menuItem => {
            routes.push({
                path: `/${menuKey}/${menuItem}`,
                component: () => import(`@/views/${menuKey}/${menuItem}.vue`), // 确保这个路径是正确的
            });
        });
    });

    return routes;
}
