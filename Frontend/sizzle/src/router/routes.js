const routes = [
  {
    path: "/home",
    component: () => import("layouts/MainLayout.vue"),
    // children: [{ path: "", component: () => import("pages/Index.vue") }],
  },

  {
    path: "/register",
    component: () => import("layouts/OutsideLayout.vue"),
    children: [{ path: "", component: () => import("pages/Register.vue") }],
  },

  {
    path: "/",
    component: () => import("layouts/OutsideLayout.vue"),
    children: [{ path: "", component: () => import("pages/Login.vue") }],
  },

  {
    path: "/profile",
    component: () => import("layouts/ProfileLayout.vue"),
    children: [{ path: "", component: () => import("pages/Profile.vue") }],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "*",
    component: () => import("pages/Error404.vue"),
  },
];

export default routes;
