import {P as l} from "./index-ea894644.js";

const E = Object.prototype.toString;

function i(n, e) {
    return E.call(n) === `[object ${e}]`
}

function R(n) {
    return i(n, "Function") || i(n, "AsyncFunction")
}

const g = n => n !== null && i(n, "Object");

function B(n) {
    return i(n, "String")
}

function S(n) {
    return /^(http|https):\/\//g.test(n)
}

function V(n) {
    return i(n, "Boolean")
}

function d(n) {
    return n && Array.isArray(n)
}

function h(n) {
    return i(n, "Number")
}

function A(n) {
    return T(n).map(e => {
        var s, _, a, u, c;
        const r = O(e), t = r ? e.children[0] : e, o = {
            ...t,
            label: (s = t.meta) == null ? void 0 : s.title,
            key: t.name,
            icon: r ? (_ = e.meta) == null ? void 0 : _.icon : (a = t.meta) == null ? void 0 : a.icon,
            src: r ? (u = e.meta) == null ? void 0 : u.src : (c = t.meta) == null ? void 0 : c.src
        };
        return t.children && t.children.length > 0 && (o.children = A(t.children)), o
    })
}

function O(n) {
    var e, r, t;
    return ((e = n.meta) == null ? void 0 : e.alwaysShow) != !0 && ((t = (r = n == null ? void 0 : n.children) == null ? void 0 : r.filter(o => {
        var s;
        return !((s = o == null ? void 0 : o.meta) != null && s.hidden)
    })) == null ? void 0 : t.length) === 1
}

function T(n) {
    return n.filter(e => {
        var r;
        return (((r = e.meta) == null ? void 0 : r.hidden) || !1) != !0 && !["/:path(.*)*", "/", l.REDIRECT, l.BASE_LOGIN].includes(e.path)
    })
}

function f(n = {}, e = {}) {
    let r;
    for (r in e) n[r] = g(n[r]) ? f(n[r], e[r]) : n[r] = e[r];
    return n
}

function p(n) {
    var e = "";
    n < 1024 ? e = n.toFixed(2) + "B" : n < 1024 * 1024 ? e = (n / 1024).toFixed(2) + "KB" : n < 1024 * 1024 * 1024 ? e = (n / (1024 * 1024)).toFixed(2) + "MB" : e = (n / (1024 * 1024 * 1024)).toFixed(2) + "GB";
    var r = e + "", t = r.indexOf("."), o = r.substring(t + 1, t + 3);
    return o == "00" ? r.substring(0, t) + r.substring(t + 3, r.length) : e
}

function G(n, e) {
    let r = "";
    e = new Date(e);
    const t = {
        "Y+": e.getFullYear().toString(),
        "m+": (e.getMonth() + 1).toString(),
        "d+": e.getDate().toString(),
        "H+": e.getHours().toString(),
        "M+": e.getMinutes().toString(),
        "S+": e.getSeconds().toString()
    };
    for (let o in t) r = new RegExp("(" + o + ")").exec(n), r && (n = n.replace(r[1], r[1].length == 1 ? t[o] : t[o].padStart(r[1].length, "0")));
    return n
}

const L = n => `__PRODUCTION__${n.VITE_GLOB_APP_SHORT_NAME || "__APP"}__CONF__`.toUpperCase().replace(/\s/g, "");

function P() {
    const n = L({
        VITE_PORT: "80",
        VITE_GLOB_APP_TITLE: "WitchPan",
        VITE_GLOB_APP_SHORT_NAME: "魔女云盘",
        BASE_URL: "/",
        MODE: "production",
        DEV: !1,
        PROD: !0,
        SSR: !1
    }), e = window[n], {
        VITE_GLOB_APP_TITLE: r,
        VITE_GLOB_API_URL: t,
        VITE_GLOB_APP_SHORT_NAME: o,
        VITE_GLOB_API_URL_PREFIX: s,
        VITE_GLOB_UPLOAD_URL: _,
        VITE_GLOB_IMG_URL: a,
        VITE_GLOB_AVATAR_URL: u
    } = e;
    return /^[a-zA-Z\_]*$/.test(o) || console.log("VITE_GLOB_APP_SHORT_NAME Variables can only be characters/underscores, please modify in the environment variables and re-running."), {
        VITE_GLOB_APP_TITLE: r,
        VITE_GLOB_API_URL: t,
        VITE_GLOB_APP_SHORT_NAME: o,
        VITE_GLOB_API_URL_PREFIX: s,
        VITE_GLOB_UPLOAD_URL: _,
        VITE_GLOB_IMG_URL: a,
        VITE_GLOB_AVATAR_URL: u
    }
}

const b = () => {
    const {
        VITE_GLOB_APP_TITLE: n,
        VITE_GLOB_API_URL: e,
        VITE_GLOB_APP_SHORT_NAME: r,
        VITE_GLOB_API_URL_PREFIX: t,
        VITE_GLOB_UPLOAD_URL: o,
        VITE_GLOB_IMG_URL: s,
        VITE_GLOB_AVATAR_URL: _
    } = P();
    return /[a-zA-Z\_]*/.test(r) || console.log("VITE_GLOB_APP_SHORT_NAME Variables can only be characters/underscores, please modify in the environment variables and re-running."), {
        title: n,
        apiUrl: e,
        shortName: r,
        urlPrefix: t,
        uploadUrl: o,
        imgUrl: s,
        avatarUrl: _
    }
};
export {h as a, B as b, g as c, G as d, R as e, f, A as g, S as h, d as i, V as j, p as s, b as u};
