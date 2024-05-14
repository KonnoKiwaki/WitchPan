import {
    r as k,
    f as re,
    g as F,
    o as v,
    c as g,
    a as i,
    s as E,
    t as M,
    h as f,
    w as S,
    F as L,
    v as H,
    j as Z,
    k as T,
    x as D,
    y as j,
    p as ee,
    n as te,
    d as ie,
    z as _e,
    b as ce,
    u as ve,
    A as W,
    B as me,
    i as B,
    C as ge,
    l as K,
    D as ye,
    e as X,
    q as ue,
    G as we,
    E as be,
    V as Q,
    H as Se,
    I as xe
} from "./index-ea894644.js";
import {s as Y, g as $e} from "./setting-8d83341a.js";
import {_ as G} from "./_plugin-vue_export-helper-c27b6911.js";
import {g as Ce, u as Ae, b as ke, L as oe, c as Ue} from "./user-56afb0b3.js";
import {A as Ve} from "./Avatar-470ce3bd.js";
import {_ as de} from "./Dialog-65b9ab6b.js";
import {V as Fe} from "./Verify-bd74e60f.js";
import {I as N} from "./Icon-9aaaa943.js";
import {u as Ie} from "./file-9dac2eef.js";

const Be = d => (ee("data-v-dd0b32d4"), d = d(), te(), d), Me = {class: "expand-menu"}, ze = {class: "text"},
    Pe = {class: "edit"}, Re = Be(() => i("i", {class: "iconfont icon-cate-edit"}, null, -1)),
    De = {class: "expand-menu-list"}, je = ["onClick"], Ee = {class: "body"}, Le = {class: "text"},
    Oe = {name: "ExpandMenu"}, Ne = Object.assign(Oe, {
        props: {modelValue: {type: Array}, title: {type: String}, activePath: {type: String}}, setup(d, {emit: V}) {
            const b = d, u = k(b.modelValue), r = k(!0), c = k([]);
            re(() => {
                c.value = b.modelValue.map(_ => {
                    if (_.isShow == null || _.isShow) return _.name
                })
            });

            function h(_) {
                const p = b.modelValue.filter(x => _.includes(x.name));
                u.value = p
            }

            function w() {
                b.modelValue && b.modelValue.length > 0 && y(b.modelValue[0])
            }

            function y(_) {
                V("item-click", {path: _.path, key: _.name})
            }

            return (_, p) => {
                const x = F("el-checkbox"), A = F("el-checkbox-group"), R = F("el-popover");
                return v(), g("div", Me, [i("div", {
                    class: E(["expand-menu-title", d.activePath == d.modelValue[0].path ? "active" : ""]),
                    onClick: w
                }, [i("i", {
                    class: E(["iconfont icon-arrow-down animation", r.value ? "" : "rotate"]),
                    onClick: p[0] || (p[0] = () => r.value = !r.value)
                }, null, 2), i("span", ze, M(d.title), 1), i("div", Pe, [f(R, {
                    placement: "right-start",
                    trigger: "click",
                    "show-arrow": !1,
                    "popper-style": "padding: 14px 0px; width: 120px; min-width:120px;border-radius:8px"
                }, {
                    reference: S(() => [Re]),
                    default: S(() => [f(A, {
                        modelValue: c.value,
                        "onUpdate:modelValue": p[1] || (p[1] = m => c.value = m),
                        onChange: h
                    }, {
                        default: S(() => [(v(!0), g(L, null, H(d.modelValue, m => (v(), g(L, null, [m.isShow == null || m.isShow ? (v(), Z(x, {
                            key: 0,
                            size: "large",
                            class: "check-item",
                            label: m.name
                        }, null, 8, ["label"])) : T("", !0)], 64))), 256))]), _: 1
                    }, 8, ["modelValue"])]),
                    _: 1
                })])], 2), D(i("div", De, [(v(!0), g(L, null, H(u.value, m => (v(), g(L, null, [m.isShow == null || m.isShow ? (v(), g("div", {
                    key: 0,
                    class: E(["expand-menu-item", m.path == d.activePath ? "active" : ""]),
                    onClick: z => y(m)
                }, [i("div", Ee, [m.icon ? (v(), g("span", {
                    key: 0,
                    class: E(["iconfont", "icon-" + m.icon])
                }, null, 2)) : T("", !0), i("span", Le, M(m.name), 1)])], 10, je)) : T("", !0)], 64))), 256))], 512), [[j, r.value]])])
            }
        }
    }), Te = G(Ne, [["__scopeId", "data-v-dd0b32d4"]]),
    qe = {xmlns: "http://www.w3.org/2000/svg", "xmlns:xlink": "http://www.w3.org/1999/xlink", viewBox: "0 0 1024 1024"},
    Ke = i("path", {
        d: "M847.9 592H152c-4.4 0-8 3.6-8 8v60c0 4.4 3.6 8 8 8h605.2L612.9 851c-4.1 5.2-.4 13 6.3 13h72.5c4.9 0 9.5-2.2 12.6-6.1l168.8-214.1c16.5-21 1.6-51.8-25.2-51.8zM872 356H266.8l144.3-183c4.1-5.2.4-13-6.3-13h-72.5c-4.9 0-9.5 2.2-12.6 6.1L150.9 380.2c-16.5 21-1.6 51.8 25.1 51.8h696c4.4 0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z",
        fill: "currentColor"
    }, null, -1), He = [Ke], Ge = ie({
        name: "SwapOutlined", render: function (V, b) {
            return v(), g("svg", qe, He)
        }
    }), Je = {xmlns: "http://www.w3.org/2000/svg", "xmlns:xlink": "http://www.w3.org/1999/xlink", viewBox: "0 0 1024 1024"},
    Qe = i("path", {
        d: "M168 504.2c1-43.7 10-86.1 26.9-126c17.3-41 42.1-77.7 73.7-109.4S337 212.3 378 195c42.4-17.9 87.4-27 133.9-27s91.5 9.1 133.8 27A341.5 341.5 0 0 1 755 268.8c9.9 9.9 19.2 20.4 27.8 31.4l-60.2 47a8 8 0 0 0 3 14.1l175.7 43c5 1.2 9.9-2.6 9.9-7.7l.8-180.9c0-6.7-7.7-10.5-12.9-6.3l-56.4 44.1C765.8 155.1 646.2 92 511.8 92C282.7 92 96.3 275.6 92 503.8a8 8 0 0 0 8 8.2h60c4.4 0 7.9-3.5 8-7.8zm756 7.8h-60c-4.4 0-7.9 3.5-8 7.8c-1 43.7-10 86.1-26.9 126c-17.3 41-42.1 77.8-73.7 109.4A342.45 342.45 0 0 1 512.1 856a342.24 342.24 0 0 1-243.2-100.8c-9.9-9.9-19.2-20.4-27.8-31.4l60.2-47a8 8 0 0 0-3-14.1l-175.7-43c-5-1.2-9.9 2.6-9.9 7.7l-.7 181c0 6.7 7.7 10.5 12.9 6.3l56.4-44.1C258.2 868.9 377.8 932 512.2 932c229.2 0 415.5-183.7 419.8-411.8a8 8 0 0 0-8-8.2z",
        fill: "currentColor"
    }, null, -1), We = [Qe], Xe = ie({
        name: "SyncOutlined", render: function (V, b) {
            return v(), g("svg", Je, We)
        }
    }), ne = 60 * 60 * 24 * 7, Ye = ({prefixKey: d = "", storage: V = localStorage} = {}) => {
        function b() {
            this.storage = V, this.prefixKey = d, this.getKey = function (u) {
                return `${this.prefixKey}${u}`.toUpperCase()
            }, this.set = function (u, r, c = ne) {
                const h = JSON.stringify({value: r, expire: c != null ? new Date().getTime() + c * 1e3 : null});
                this.storage.setItem(this.getKey(u), h)
            }, this.get = function (u, r = null) {
                const c = this.storage.getItem(this.getKey(u));
                if (c) try {
                    const h = JSON.parse(c), {value: w, expire: y} = h;
                    if (y === null || y >= Date.now()) return w;
                    this.remove(u)
                } catch {
                    return r
                }
                return r
            }, this.remove = function (u) {
                this.storage.removeItem(this.getKey(u))
            }, this.clear = function () {
                this.storage.clear()
            }, this.setCookie = function (u, r, c = ne) {
                document.cookie = `${this.getKey(u)}=${r}; Max-Age=${c}`
            }, this.getCookie = function (u) {
                const r = document.cookie.split("; ");
                for (let c = 0, h = r.length; c < h; c++) {
                    const w = r[c].split("=");
                    if (w[0] === this.getKey(u)) return w[1]
                }
                return ""
            }, this.removeCookie = function (u) {
                this.setCookie(u, 1, -1)
            }, this.clearCookie = function () {
                const u = document.cookie.match(/[^ =;]+(?==)/g);
                if (u) for (let r = u.length; r--;) document.cookie = u[r] + "=0;expire=" + new Date(0).toUTCString()
            }
        }

        return new b
    }, ae = Ye(), le = "USER_SPACE", pe = _e({
        id: "app-user",
        state: () => ({userSpace: ae.get(le, {useSpace: 0, totalSpace: 1})}),
        getters: {
            getUserSapce() {
                return this.userSpace
            }
        },
        actions: {
            setUserSapce(d) {
                this.userSpace = d
            }, async fetchUserSpace() {
                try {
                    const d = await Ce(), V = 7 * 24 * 60 * 60;
                    return ae.set(le, d, V), this.setUserSapce(d), Promise.resolve(d)
                } catch (d) {
                    return Promise.reject(d)
                }
            }
        }
    });
const Ze = {class: "menu"}, et = {class: "menu-list"}, tt = ["onClick"], st = {key: 1, class: "item-img"}, ot = ["src"],
    nt = ["src"], at = {class: "text"}, lt = {class: "menu-sub-list"}, rt = {key: 0}, it = ["onClick"],
    ct = {class: "body"}, ut = {class: "text"}, dt = {key: 0, class: "tips"}, pt = {class: "space-info"},
    ft = {class: "percent"}, ht = {class: "space-use"}, _t = {class: "use"}, vt = {name: "Menu"},
    mt = Object.assign(vt, {
        setup(d) {
            const V = ce(), b = ve(), u = pe(), r = k([]), c = k({}), h = k(),
                w = W(() => (u == null ? void 0 : u.userSpace) || {useSpace: 0, totalSpace: 1}),
                y = W(() => Math.floor(w.value.useSpace / w.value.totalSpace * 1e4) / 100);

            function _(A) {
                !A.path || A.key == c.value.key || V.push(A.path)
            }

            function p(A) {
                r.value.length === 0 && (r.value = $e(ge));
                const R = A.substring(0, A.lastIndexOf("/")), m = r.value.find(z => z.path === R);
                c.value = m || {}, h.value = A
            }

            const x = async () => {
                await u.fetchUserSpace()
            };
            return me(() => {
                p(b.path)
            }), re(() => {
                x()
            }), (A, R) => {
                const m = F("el-progress"), z = F("el-icon");
                return v(), g("div", Ze, [i("div", et, [(v(!0), g(L, null, H(r.value, l => (v(), g("div", {
                    class: E(["menu-item", l.key === c.value.key ? "active" : ""]),
                    onClick: a => _(l)
                }, [l.icon ? (v(), g("div", {
                    key: 0,
                    class: E(["iconfont", "icon-" + l.icon])
                }, null, 2)) : l.src ? (v(), g("div", st, [D(i("img", {
                    src: l.src[0],
                    alt: ""
                }, null, 8, ot), [[j, l.key === c.value.key]]), D(i("img", {
                    src: l.src[1],
                    alt: ""
                }, null, 8, nt), [[j, l.key !== c.value.key]])])) : T("", !0), i("div", at, M(l.label), 1)], 10, tt))), 256))]), i("div", lt, [(v(!0), g(L, null, H(c.value.children, l => (v(), g(L, null, [l.meta && l.meta.children ? (v(), g("div", rt, [f(Te, {
                    title: l.label,
                    modelValue: l.meta.children,
                    "onUpdate:modelValue": a => l.meta.children = a,
                    activePath: h.value,
                    onItemClick: _
                }, null, 8, ["title", "modelValue", "onUpdate:modelValue", "activePath"])])) : l ? (v(), g("div", {
                    key: 1,
                    class: E(["menu-sub-item", l.path == h.value ? "active" : ""]),
                    onClick: a => _(l)
                }, [i("div", ct, [i("span", ut, M(l.label), 1)])], 10, it)) : T("", !0)], 64))), 256)), c.value && c.value.tips ? (v(), g("div", dt, M(c.value.tips), 1)) : T("", !0), i("div", pt, [i("div", ft, [f(m, {
                    percentage: y.value,
                    "show-text": !1,
                    color: "#ffd821"
                }, null, 8, ["percentage"])]), i("div", ht, [i("div", _t, M(B(Y)(w.value.useSpace)) + " / " + M(B(Y)(w.value.totalSpace)), 1), f(z, {
                    class: "refresh",
                    size: 14,
                    onClick: x
                }, {default: S(() => [f(B(Xe))]), _: 1})])])])])
            }
        }
    }), gt = G(mt, [["__scopeId", "data-v-a592c8de"]]);
const yt = {class: "avatar-upload"}, wt = {class: "avatar-show"}, bt = ["src"], St = ["src"], xt = ["src"],
    $t = {class: "select-btn"}, Ct = {name: "AvatarUpload"}, At = Object.assign(Ct, {
        props: {modelValue: {type: Object, default: null}}, setup(d, {emit: V}) {
            const {proxy: b} = ye(), u = k(new Date), r = k(null), c = async h => {
                h = h.file;
                let w = new FileReader;
                w.readAsDataURL(h), w.onload = ({target: y}) => {
                    r.value = y.result
                }, V("update:modelValue", h)
            };
            return (h, w) => {
                const y = F("el-button"), _ = F("el-upload");
                return v(), g("div", yt, [i("div", wt, [r.value ? (v(), g("img", {
                    key: 0,
                    src: r.value
                }, null, 8, bt)) : (v(), g(L, {key: 1}, [d.modelValue && d.modelValue.qqAvatar ? (v(), g("img", {
                    key: 0,
                    src: `${d.modelValue.qqAvatar}`
                }, null, 8, St)) : (v(), g("img", {
                    key: 1,
                    src: `${B(b).globalInfo.avatarUrl}${d.modelValue.userId}?${u.value}`
                }, null, 8, xt))], 64))]), i("div", $t, [f(_, {
                    name: "file",
                    "show-file-list": !1,
                    accept: ".png,.PNG,.jpg,.JPG,.jpeg,.gif,.GIF,.bmp,.MP",
                    multiple: !1,
                    "http-request": c
                }, {default: S(() => [f(y, {type: "primary"}, {default: S(() => [K("选择")]), _: 1})]), _: 1})])])
            }
        }
    }), kt = {name: "UploadAvatar"}, Ut = Object.assign(kt, {
        setup(d, {expose: V, emit: b}) {
            const u = X({
                show: !1, title: "修改头像", buttons: [{
                    type: "primary", text: "确定", click: y => {
                        w()
                    }
                }]
            }), r = k(), c = k({});
            V({
                show: y => {
                    c.value = Object.assign({}, y), c.value.avatar = {userId: y.id, qqAvatar: y.avatar}, u.show = !0
                }
            });
            const w = async () => {
                if (!(c.value.avatar instanceof File)) {
                    u.show = !1;
                    return
                }
                const y = new FormData;
                y.append("avatar", c.value.avatar), await Ae(y), u.show = !1, b("upload-success")
            };
            return (y, _) => {
                const p = F("el-form-item"), x = F("el-form");
                return v(), Z(de, {
                    show: u.show,
                    title: u.title,
                    buttons: u.buttons,
                    width: "500px",
                    showCancel: !1,
                    onClose: _[1] || (_[1] = A => u.show = !1)
                }, {
                    default: S(() => [f(x, {
                        ref_key: "formDataRef",
                        ref: r,
                        model: c.value,
                        "label-width": "80px"
                    }, {
                        default: S(() => [f(p, {label: "昵称"}, {
                            default: S(() => [K(M(c.value.nickname), 1)]),
                            _: 1
                        }), f(p, {label: "头像上传"}, {
                            default: S(() => [f(At, {
                                modelValue: c.value.avatar,
                                "onUpdate:modelValue": _[0] || (_[0] = A => c.value.avatar = A)
                            }, null, 8, ["modelValue"])]), _: 1
                        })]), _: 1
                    }, 8, ["model"])]), _: 1
                }, 8, ["show", "title", "buttons"])
            }
        }
    }), Vt = {name: "UploadPassword"}, Ft = Object.assign(Vt, {
        setup(d, {expose: V}) {
            const b = (_, p, x) => {
                p !== r.value.password ? x(new Error(_.message)) : x()
            }, u = X({
                password: [{required: !0, message: "请输入密码", trigger: "blur"}, {
                    validator: Fe.password,
                    message: "密码只能是数字, 字母, 特殊字符8-18位",
                    trigger: "blur"
                }],
                rePassword: [{required: !0, message: "请再次输入密码", trigger: "blur"}, {
                    validator: b,
                    message: "两次密码不一致",
                    trigger: "blur"
                }]
            }), r = k({}), c = k(), h = X({
                show: !1, title: "修改密码", buttons: [{
                    type: "primary", text: "确定", click: _ => {
                        y()
                    }
                }]
            });
            V({
                show: () => {
                    h.show = !0, ue(() => {
                        c.value.resetFields(), r.value = {}
                    })
                }
            });
            const y = () => {
                c.value.validate(async _ => {
                    _ && (await ke({password: r.value.password}), h.show = !1)
                })
            };
            return (_, p) => {
                const x = F("el-input"), A = F("el-form-item"), R = F("el-form");
                return v(), g("div", null, [f(de, {
                    show: h.show,
                    title: h.title,
                    buttons: h.buttons,
                    width: "400px",
                    showCancel: !1,
                    onClose: p[2] || (p[2] = m => h.show = !1)
                }, {
                    default: S(() => [f(R, {
                        ref_key: "formDataRef",
                        ref: c,
                        model: r.value,
                        "label-width": "80px",
                        rules: u
                    }, {
                        default: S(() => [f(A, {
                            label: "新密码",
                            prop: "password"
                        }, {
                            default: S(() => [f(x, {
                                "show-password": "",
                                type: "password",
                                placeholder: "请输入密码",
                                modelValue: r.value.password,
                                "onUpdate:modelValue": p[0] || (p[0] = m => r.value.password = m),
                                modelModifiers: {trim: !0},
                                "prefix-icon": B(oe),
                                maxLength: "20"
                            }, null, 8, ["modelValue", "prefix-icon"])]), _: 1
                        }), f(A, {label: "确认密码", prop: "rePassword"}, {
                            default: S(() => [f(x, {
                                "show-password": "",
                                type: "password",
                                placeholder: "请再次输入密码",
                                modelValue: r.value.rePassword,
                                "onUpdate:modelValue": p[1] || (p[1] = m => r.value.rePassword = m),
                                modelModifiers: {trim: !0},
                                "prefix-icon": B(oe),
                                maxLength: "20"
                            }, null, 8, ["modelValue", "prefix-icon"])]), _: 1
                        })]), _: 1
                    }, 8, ["model", "rules"])]), _: 1
                }, 8, ["show", "title", "buttons"])])
            }
        }
    }), U = {
        empty: {value: "empty", desc: "文件为空", color: "#F75000", icon: "close"},
        fail: {value: "fail", desc: "上传失败", color: "#F75000", icon: "close"},
        init: {value: "init", desc: "解析中", color: "#e6a23c", icon: "clock"},
        uploading: {value: "uploading", desc: "上传中", color: "#409eff", icon: "upload"},
        upload_finish: {value: "upload_finish", desc: "上传完成", color: "#67c23a", icon: "ok"},
        upload_seconds: {value: "upload_seconds", desc: "秒传", color: "#67c23a", icon: "ok"}
    };
var fe = {exports: {}};
(function (d, V) {
    (function (b) {
        d.exports = b()
    })(function (b) {
        var u = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"];

        function r(a, n) {
            var e = a[0], t = a[1], o = a[2], s = a[3];
            e += (t & o | ~t & s) + n[0] - 680876936 | 0, e = (e << 7 | e >>> 25) + t | 0, s += (e & t | ~e & o) + n[1] - 389564586 | 0, s = (s << 12 | s >>> 20) + e | 0, o += (s & e | ~s & t) + n[2] + 606105819 | 0, o = (o << 17 | o >>> 15) + s | 0, t += (o & s | ~o & e) + n[3] - 1044525330 | 0, t = (t << 22 | t >>> 10) + o | 0, e += (t & o | ~t & s) + n[4] - 176418897 | 0, e = (e << 7 | e >>> 25) + t | 0, s += (e & t | ~e & o) + n[5] + 1200080426 | 0, s = (s << 12 | s >>> 20) + e | 0, o += (s & e | ~s & t) + n[6] - 1473231341 | 0, o = (o << 17 | o >>> 15) + s | 0, t += (o & s | ~o & e) + n[7] - 45705983 | 0, t = (t << 22 | t >>> 10) + o | 0, e += (t & o | ~t & s) + n[8] + 1770035416 | 0, e = (e << 7 | e >>> 25) + t | 0, s += (e & t | ~e & o) + n[9] - 1958414417 | 0, s = (s << 12 | s >>> 20) + e | 0, o += (s & e | ~s & t) + n[10] - 42063 | 0, o = (o << 17 | o >>> 15) + s | 0, t += (o & s | ~o & e) + n[11] - 1990404162 | 0, t = (t << 22 | t >>> 10) + o | 0, e += (t & o | ~t & s) + n[12] + 1804603682 | 0, e = (e << 7 | e >>> 25) + t | 0, s += (e & t | ~e & o) + n[13] - 40341101 | 0, s = (s << 12 | s >>> 20) + e | 0, o += (s & e | ~s & t) + n[14] - 1502002290 | 0, o = (o << 17 | o >>> 15) + s | 0, t += (o & s | ~o & e) + n[15] + 1236535329 | 0, t = (t << 22 | t >>> 10) + o | 0, e += (t & s | o & ~s) + n[1] - 165796510 | 0, e = (e << 5 | e >>> 27) + t | 0, s += (e & o | t & ~o) + n[6] - 1069501632 | 0, s = (s << 9 | s >>> 23) + e | 0, o += (s & t | e & ~t) + n[11] + 643717713 | 0, o = (o << 14 | o >>> 18) + s | 0, t += (o & e | s & ~e) + n[0] - 373897302 | 0, t = (t << 20 | t >>> 12) + o | 0, e += (t & s | o & ~s) + n[5] - 701558691 | 0, e = (e << 5 | e >>> 27) + t | 0, s += (e & o | t & ~o) + n[10] + 38016083 | 0, s = (s << 9 | s >>> 23) + e | 0, o += (s & t | e & ~t) + n[15] - 660478335 | 0, o = (o << 14 | o >>> 18) + s | 0, t += (o & e | s & ~e) + n[4] - 405537848 | 0, t = (t << 20 | t >>> 12) + o | 0, e += (t & s | o & ~s) + n[9] + 568446438 | 0, e = (e << 5 | e >>> 27) + t | 0, s += (e & o | t & ~o) + n[14] - 1019803690 | 0, s = (s << 9 | s >>> 23) + e | 0, o += (s & t | e & ~t) + n[3] - 187363961 | 0, o = (o << 14 | o >>> 18) + s | 0, t += (o & e | s & ~e) + n[8] + 1163531501 | 0, t = (t << 20 | t >>> 12) + o | 0, e += (t & s | o & ~s) + n[13] - 1444681467 | 0, e = (e << 5 | e >>> 27) + t | 0, s += (e & o | t & ~o) + n[2] - 51403784 | 0, s = (s << 9 | s >>> 23) + e | 0, o += (s & t | e & ~t) + n[7] + 1735328473 | 0, o = (o << 14 | o >>> 18) + s | 0, t += (o & e | s & ~e) + n[12] - 1926607734 | 0, t = (t << 20 | t >>> 12) + o | 0, e += (t ^ o ^ s) + n[5] - 378558 | 0, e = (e << 4 | e >>> 28) + t | 0, s += (e ^ t ^ o) + n[8] - 2022574463 | 0, s = (s << 11 | s >>> 21) + e | 0, o += (s ^ e ^ t) + n[11] + 1839030562 | 0, o = (o << 16 | o >>> 16) + s | 0, t += (o ^ s ^ e) + n[14] - 35309556 | 0, t = (t << 23 | t >>> 9) + o | 0, e += (t ^ o ^ s) + n[1] - 1530992060 | 0, e = (e << 4 | e >>> 28) + t | 0, s += (e ^ t ^ o) + n[4] + 1272893353 | 0, s = (s << 11 | s >>> 21) + e | 0, o += (s ^ e ^ t) + n[7] - 155497632 | 0, o = (o << 16 | o >>> 16) + s | 0, t += (o ^ s ^ e) + n[10] - 1094730640 | 0, t = (t << 23 | t >>> 9) + o | 0, e += (t ^ o ^ s) + n[13] + 681279174 | 0, e = (e << 4 | e >>> 28) + t | 0, s += (e ^ t ^ o) + n[0] - 358537222 | 0, s = (s << 11 | s >>> 21) + e | 0, o += (s ^ e ^ t) + n[3] - 722521979 | 0, o = (o << 16 | o >>> 16) + s | 0, t += (o ^ s ^ e) + n[6] + 76029189 | 0, t = (t << 23 | t >>> 9) + o | 0, e += (t ^ o ^ s) + n[9] - 640364487 | 0, e = (e << 4 | e >>> 28) + t | 0, s += (e ^ t ^ o) + n[12] - 421815835 | 0, s = (s << 11 | s >>> 21) + e | 0, o += (s ^ e ^ t) + n[15] + 530742520 | 0, o = (o << 16 | o >>> 16) + s | 0, t += (o ^ s ^ e) + n[2] - 995338651 | 0, t = (t << 23 | t >>> 9) + o | 0, e += (o ^ (t | ~s)) + n[0] - 198630844 | 0, e = (e << 6 | e >>> 26) + t | 0, s += (t ^ (e | ~o)) + n[7] + 1126891415 | 0, s = (s << 10 | s >>> 22) + e | 0, o += (e ^ (s | ~t)) + n[14] - 1416354905 | 0,o = (o << 15 | o >>> 17) + s | 0,t += (s ^ (o | ~e)) + n[5] - 57434055 | 0,t = (t << 21 | t >>> 11) + o | 0,e += (o ^ (t | ~s)) + n[12] + 1700485571 | 0,e = (e << 6 | e >>> 26) + t | 0,s += (t ^ (e | ~o)) + n[3] - 1894986606 | 0,s = (s << 10 | s >>> 22) + e | 0,o += (e ^ (s | ~t)) + n[10] - 1051523 | 0,o = (o << 15 | o >>> 17) + s | 0,t += (s ^ (o | ~e)) + n[1] - 2054922799 | 0,t = (t << 21 | t >>> 11) + o | 0,e += (o ^ (t | ~s)) + n[8] + 1873313359 | 0,e = (e << 6 | e >>> 26) + t | 0,s += (t ^ (e | ~o)) + n[15] - 30611744 | 0,s = (s << 10 | s >>> 22) + e | 0,o += (e ^ (s | ~t)) + n[6] - 1560198380 | 0,o = (o << 15 | o >>> 17) + s | 0,t += (s ^ (o | ~e)) + n[13] + 1309151649 | 0,t = (t << 21 | t >>> 11) + o | 0,e += (o ^ (t | ~s)) + n[4] - 145523070 | 0,e = (e << 6 | e >>> 26) + t | 0,s += (t ^ (e | ~o)) + n[11] - 1120210379 | 0,s = (s << 10 | s >>> 22) + e | 0,o += (e ^ (s | ~t)) + n[2] + 718787259 | 0,o = (o << 15 | o >>> 17) + s | 0,t += (s ^ (o | ~e)) + n[9] - 343485551 | 0,t = (t << 21 | t >>> 11) + o | 0,a[0] = e + a[0] | 0,a[1] = t + a[1] | 0,a[2] = o + a[2] | 0,a[3] = s + a[3] | 0
        }

        function c(a) {
            var n = [], e;
            for (e = 0; e < 64; e += 4) n[e >> 2] = a.charCodeAt(e) + (a.charCodeAt(e + 1) << 8) + (a.charCodeAt(e + 2) << 16) + (a.charCodeAt(e + 3) << 24);
            return n
        }

        function h(a) {
            var n = [], e;
            for (e = 0; e < 64; e += 4) n[e >> 2] = a[e] + (a[e + 1] << 8) + (a[e + 2] << 16) + (a[e + 3] << 24);
            return n
        }

        function w(a) {
            var n = a.length, e = [1732584193, -271733879, -1732584194, 271733878], t, o, s, $, C, I;
            for (t = 64; t <= n; t += 64) r(e, c(a.substring(t - 64, t)));
            for (a = a.substring(t - 64), o = a.length, s = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], t = 0; t < o; t += 1) s[t >> 2] |= a.charCodeAt(t) << (t % 4 << 3);
            if (s[t >> 2] |= 128 << (t % 4 << 3), t > 55) for (r(e, s), t = 0; t < 16; t += 1) s[t] = 0;
            return $ = n * 8, $ = $.toString(16).match(/(.*?)(.{0,8})$/), C = parseInt($[2], 16), I = parseInt($[1], 16) || 0, s[14] = C, s[15] = I, r(e, s), e
        }

        function y(a) {
            var n = a.length, e = [1732584193, -271733879, -1732584194, 271733878], t, o, s, $, C, I;
            for (t = 64; t <= n; t += 64) r(e, h(a.subarray(t - 64, t)));
            for (a = t - 64 < n ? a.subarray(t - 64) : new Uint8Array(0), o = a.length, s = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], t = 0; t < o; t += 1) s[t >> 2] |= a[t] << (t % 4 << 3);
            if (s[t >> 2] |= 128 << (t % 4 << 3), t > 55) for (r(e, s), t = 0; t < 16; t += 1) s[t] = 0;
            return $ = n * 8, $ = $.toString(16).match(/(.*?)(.{0,8})$/), C = parseInt($[2], 16), I = parseInt($[1], 16) || 0, s[14] = C, s[15] = I, r(e, s), e
        }

        function _(a) {
            var n = "", e;
            for (e = 0; e < 4; e += 1) n += u[a >> e * 8 + 4 & 15] + u[a >> e * 8 & 15];
            return n
        }

        function p(a) {
            var n;
            for (n = 0; n < a.length; n += 1) a[n] = _(a[n]);
            return a.join("")
        }

        p(w("hello")), typeof ArrayBuffer < "u" && !ArrayBuffer.prototype.slice && function () {
            function a(n, e) {
                return n = n | 0 || 0, n < 0 ? Math.max(n + e, 0) : Math.min(n, e)
            }

            ArrayBuffer.prototype.slice = function (n, e) {
                var t = this.byteLength, o = a(n, t), s = t, $, C, I, P;
                return e !== b && (s = a(e, t)), o > s ? new ArrayBuffer(0) : ($ = s - o, C = new ArrayBuffer($), I = new Uint8Array(C), P = new Uint8Array(this, o, $), I.set(P), C)
            }
        }();

        function x(a) {
            return /[\u0080-\uFFFF]/.test(a) && (a = unescape(encodeURIComponent(a))), a
        }

        function A(a, n) {
            var e = a.length, t = new ArrayBuffer(e), o = new Uint8Array(t), s;
            for (s = 0; s < e; s += 1) o[s] = a.charCodeAt(s);
            return n ? o : t
        }

        function R(a) {
            return String.fromCharCode.apply(null, new Uint8Array(a))
        }

        function m(a, n, e) {
            var t = new Uint8Array(a.byteLength + n.byteLength);
            return t.set(new Uint8Array(a)), t.set(new Uint8Array(n), a.byteLength), e ? t : t.buffer
        }

        function z(a) {
            var n = [], e = a.length, t;
            for (t = 0; t < e - 1; t += 2) n.push(parseInt(a.substr(t, 2), 16));
            return String.fromCharCode.apply(String, n)
        }

        function l() {
            this.reset()
        }

        return l.prototype.append = function (a) {
            return this.appendBinary(x(a)), this
        }, l.prototype.appendBinary = function (a) {
            this._buff += a, this._length += a.length;
            var n = this._buff.length, e;
            for (e = 64; e <= n; e += 64) r(this._hash, c(this._buff.substring(e - 64, e)));
            return this._buff = this._buff.substring(e - 64), this
        }, l.prototype.end = function (a) {
            var n = this._buff, e = n.length, t, o = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], s;
            for (t = 0; t < e; t += 1) o[t >> 2] |= n.charCodeAt(t) << (t % 4 << 3);
            return this._finish(o, e), s = p(this._hash), a && (s = z(s)), this.reset(), s
        }, l.prototype.reset = function () {
            return this._buff = "", this._length = 0, this._hash = [1732584193, -271733879, -1732584194, 271733878], this
        }, l.prototype.getState = function () {
            return {buff: this._buff, length: this._length, hash: this._hash.slice()}
        }, l.prototype.setState = function (a) {
            return this._buff = a.buff, this._length = a.length, this._hash = a.hash, this
        }, l.prototype.destroy = function () {
            delete this._hash, delete this._buff, delete this._length
        }, l.prototype._finish = function (a, n) {
            var e = n, t, o, s;
            if (a[e >> 2] |= 128 << (e % 4 << 3), e > 55) for (r(this._hash, a), e = 0; e < 16; e += 1) a[e] = 0;
            t = this._length * 8, t = t.toString(16).match(/(.*?)(.{0,8})$/), o = parseInt(t[2], 16), s = parseInt(t[1], 16) || 0, a[14] = o, a[15] = s, r(this._hash, a)
        }, l.hash = function (a, n) {
            return l.hashBinary(x(a), n)
        }, l.hashBinary = function (a, n) {
            var e = w(a), t = p(e);
            return n ? z(t) : t
        }, l.ArrayBuffer = function () {
            this.reset()
        }, l.ArrayBuffer.prototype.append = function (a) {
            var n = m(this._buff.buffer, a, !0), e = n.length, t;
            for (this._length += a.byteLength, t = 64; t <= e; t += 64) r(this._hash, h(n.subarray(t - 64, t)));
            return this._buff = t - 64 < e ? new Uint8Array(n.buffer.slice(t - 64)) : new Uint8Array(0), this
        }, l.ArrayBuffer.prototype.end = function (a) {
            var n = this._buff, e = n.length, t = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], o, s;
            for (o = 0; o < e; o += 1) t[o >> 2] |= n[o] << (o % 4 << 3);
            return this._finish(t, e), s = p(this._hash), a && (s = z(s)), this.reset(), s
        }, l.ArrayBuffer.prototype.reset = function () {
            return this._buff = new Uint8Array(0), this._length = 0, this._hash = [1732584193, -271733879, -1732584194, 271733878], this
        }, l.ArrayBuffer.prototype.getState = function () {
            var a = l.prototype.getState.call(this);
            return a.buff = R(a.buff), a
        }, l.ArrayBuffer.prototype.setState = function (a) {
            return a.buff = A(a.buff, !0), l.prototype.setState.call(this, a)
        }, l.ArrayBuffer.prototype.destroy = l.prototype.destroy, l.ArrayBuffer.prototype._finish = l.prototype._finish, l.ArrayBuffer.hash = function (a, n) {
            var e = y(new Uint8Array(a)), t = p(e);
            return n ? z(t) : t
        }, l
    })
})(fe);
var It = fe.exports;
const Bt = we(It);
const se = d => (ee("data-v-11f9e00e"), d = d(), te(), d), Mt = {class: "uploader-panel"},
    zt = {class: "uploader-header"}, Pt = {class: "uploader-body"}, Rt = {class: "file-list"},
    Dt = {class: "file-item"}, jt = {class: "file-info"}, Et = se(() => i("div", {class: "file-icon"}, [i("img", {
        src: "https://staticwx.cdn.bcebos.com/mini-program/images/ic_image_v2.png",
        alt: ""
    })], -1)), Lt = {class: "file-progress"}, Ot = {class: "file-status"}, Nt = {class: "error"}, Tt = {class: "cursor-p"},
    qt = {class: "file-size"}, Kt = se(() => i("span", {class: "speed"}, null, -1)), Ht = {class: "file-operate"},
    Gt = {title: "所在文件夹", class: "common-icon-container"}, Jt = ["onClick"], Qt = ["onClick"], Wt = ["onClick"],
    Xt = ["onClick"], Yt = se(() => i("span", {class: "always-tips"}, "- 仅展示本次上传任务 -", -1)),
    Zt = {name: "FileUploader", components: {Icon: N}}, es = Object.assign(Zt, {
        emits: ["uploadCallback"], setup(d, {expose: V, emit: b}) {
            const r = k([]), c = k([]),
                h = W(() => r.value.filter(l => l.status == U.upload_finish.value || l.status == U.upload_seconds.value).length);

            function w(l) {
                return l == U.uploading.value || l == U.init.value
            }

            V({
                addFile: async (l, a) => {
                    const n = {
                        file: l,
                        uid: l.uid,
                        md5: null,
                        filename: l.name,
                        status: U.init.value,
                        uploadSize: 0,
                        totalSize: l.size,
                        progress: 0,
                        pause: !1,
                        cancel: !1,
                        chunkIndex: 0,
                        filePid: a,
                        errorMsg: null
                    };
                    if (r.value.unshift(n), n.totalSize == 0) {
                        n.status = U.empty.value;
                        return
                    }
                    let e = await _(n);
                    e != null && x(e)
                }
            });

            async function _(l) {
                let a = l.file, n = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice,
                    e = Math.ceil(a.size / 5242880), t = 0, o = new Bt.ArrayBuffer, s = new FileReader, $ = () => {
                        let C = t * 5242880, I = C + 5242880 >= a.size ? a.size : 5242880;
                        s.readAsArrayBuffer(n.call(a, C, I))
                    };
                return $(), new Promise((C, I) => {
                    let P = p(a.uid);
                    P.status = U.init.value, s.onload = O => {
                        if (o.append(O.target.result), t++, t < e) {
                            let q = Math.floor(t / e * 100);
                            P.progress = q, $()
                        } else {
                            let q = o.end();
                            o.destroy(), P.progress = 100, P.md5 = q, P.progress = 0, P.status = U.uploading.value, C(l.uid)
                        }
                    }, s.onerror = () => {
                        P.progress = -1, P.status = U.fail.value, C(l.uid)
                    }
                }).catch(C => (console.log(C), null))
            }

            const p = l => r.value.find(n => n.file.uid == l), x = async (l, a) => {
                a = a || 0;
                let n = p(l);
                const e = n.file, t = n.totalSize, o = Math.ceil(t / 5242880);
                for (let s = a; s < o; s++) {
                    let $ = c.value.indexOf(l);
                    if ($ != -1) {
                        c.value.splice($, 1);
                        break
                    }
                    if (n = p(l), n.pause || n.cancel) break;
                    let C = s * 5242880, I = C + 5242880 >= t ? t : C + 5242880, P = e.slice(C, I);
                    try {
                        let O = await Ie({
                            file: P,
                            filename: e.name,
                            fileMd5: n.md5,
                            chunkIndex: s,
                            chunks: o,
                            id: n.id,
                            filePid: n.filePid
                        }, {
                            onUploadProgress: q => {
                                let J = q.loaded;
                                J > t && (J = t), n.uploadSize = s * 5242880 + J, n.progress = Math.floor(n.uploadSize / t * 100)
                            }
                        });
                        if (n.id = O.id, n.status = U[O.status].value, n.chunkIndex = s, O.status == U.upload_seconds.value || O.status == U.upload_finish.value) {
                            be.success("文件上传成功～"), n.progress = 100, b("uploadCallback");
                            break
                        }
                    } catch (O) {
                        console.log(O), n.status = U.fail.value
                    }
                }
            };

            function A(l) {
                p(l).pause = !0
            }

            function R(l) {
                const a = p(l);
                a.pause = !1, x(a.uid, a.chunkIndex + 1)
            }

            function m(l) {
                p(l).cancel = !0
            }

            function z(l) {
                const a = p(l);
                a.cancel = !1, x(a.uid)
            }

            return (l, a) => {
                const n = F("el-progress");
                return v(), g("div", Mt, [i("div", zt, [i("span", null, "上传完成（" + M(h.value) + "/" + M(r.value.length) + "）", 1)]), i("div", Pt, [i("ul", Rt, [(v(!0), g(L, null, H(r.value, (e, t) => (v(), g("li", Dt, [i("div", jt, [Et, i("div", Lt, [i("div", {class: E(["name-text", !w(e.status) || e.cancel ? "no-progress" : ""])}, M(e.filename), 3), D(f(n, {
                    percentage: e.progress,
                    "stroke-width": 3,
                    striped: "",
                    "show-text": !1,
                    "striped-flow": "",
                    duration: 20
                }, null, 8, ["percentage"]), [[j, w(e.status) && !e.cancel]]), i("div", {class: E(["status", !w(e.status) || e.cancel ? "no-progress" : ""])}, [D(i("span", Ot, [i("span", Nt, [i("span", Tt, M(B(U).init.desc), 1)])], 512), [[j, e == B(U).init.value]]), i("div", qt, M(B(Y)(e.totalSize)), 1), Kt], 2)])]), i("div", Ht, [D(i("span", Gt, [f(N, {
                    iconName: "open_folder",
                    "custom-class": "img",
                    width: 28
                })], 512), [[j, e.status == B(U).upload_finish.value || e.status == B(U).upload_seconds.value]]), D(i("span", {
                    title: "暂停",
                    class: "common-icon-container",
                    onClick: o => A(e.uid)
                }, [f(N, {
                    iconName: "pause",
                    "custom-class": "img",
                    width: 28
                })], 8, Jt), [[j, e.status == B(U).uploading.value && !e.pause && !e.cancel]]), D(i("span", {
                    title: "继续上传",
                    class: "common-icon-container",
                    onClick: o => R(e.uid)
                }, [f(N, {
                    iconName: "reupload",
                    "custom-class": "img",
                    width: 28
                })], 8, Qt), [[j, e.status == B(U).uploading.value && e.pause]]), D(i("span", {
                    title: "重试",
                    class: "common-icon-container",
                    onClick: o => z(e.uid)
                }, [f(N, {
                    iconName: "retry",
                    "custom-class": "img",
                    width: 28
                })], 8, Wt), [[j, e.cancel]]), D(i("span", {
                    title: "取消",
                    class: "common-icon-container",
                    onClick: o => m(e.uid)
                }, [f(N, {
                    iconName: "cancel",
                    "custom-class": "img",
                    width: 28
                })], 8, Xt), [[j, (e.status == B(U).uploading.value || e.status == B(U).init.value) && !e.cancel]])])]))), 256))]), Yt])])
            }
        }
    }), he = G(es, [["__scopeId", "data-v-11f9e00e"]]);
const ts = d => (ee("data-v-e3863dc4"), d = d(), te(), d), ss = {class: "framework"}, os = {class: "header"},
    ns = ts(() => i("div", {class: "logo"}, [i("div", {class: "name"}, "魔女云盘")], -1)), as = {class: "right-panel"},
    ls = {class: "btn-radius"}, rs = {class: "user-info"}, is = {class: "avatar"}, cs = {class: "nickname"},
    us = {class: "body"}, ds = {class: "left-sider"}, ps = {class: "body-content"},
    fs = {name: "Layout", components: {FileUploader: he}}, hs = Object.assign(fs, {
        setup(d) {
            const V = ce(), b = pe(), u = k(0), r = k(), c = k(), h = k(), w = k(), y = k(), _ = k(!1),
                p = k(Q.get("userInfo")), x = a => {
                    const {file: n, filePid: e} = a;
                    _.value = !0, h.value.addFile(n, e)
                }, A = () => {
                    ue(async () => {
                        var a;
                        (a = y.value) == null || a.reloadTable(), await b.fetchUserSpace()
                    })
                }, R = () => {
                    r.value.show(p.value)
                }, m = () => {
                    c.value.show()
                }, z = () => {
                    delete p.value.avatar, Q.set("userInfo", p.value, 0), u.value = new Date().getTime()
                }, l = () => {
                    xe.confirm("你确定要退出吗？", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        draggable: !0,
                        type: "info"
                    }).then(async () => {
                        await Ue(), Q.remove("userInfo"), V.push("login")
                    }).catch(() => {
                    })
                };
            return (a, n) => {
                const e = F("el-icon"), t = F("el-popover"), o = F("el-dropdown-item"), s = F("el-dropdown-menu"),
                    $ = F("el-dropdown"), C = F("router-view");
                return v(), g("div", ss, [i("div", os, [ns, i("div", as, [f(t, {
                    placement: "bottom",
                    width: "auto",
                    "popper-style": "padding:0px;border-radius:8px;",
                    trigger: "click",
                    offset: 30,
                    "hide-after": 10,
                    visible: _.value,
                    "onUpdate:visible": n[0] || (n[0] = I => _.value = I)
                }, {
                    reference: S(() => [i("span", ls, [f(e, {
                        class: "rotate",
                        color: "#454d5a"
                    }, {default: S(() => [f(B(Ge))]), _: 1})])]),
                    default: S(() => [f(he, {ref_key: "uploaderRef", ref: h, onUploadCallback: A}, null, 512)]),
                    _: 1
                }, 8, ["visible"]), f($, null, {
                    dropdown: S(() => [f(s, null, {
                        default: S(() => [f(o, {onClick: R}, {
                            default: S(() => [K("修改头像")]),
                            _: 1
                        }), f(o, {onClick: m}, {
                            default: S(() => [K("修改密码")]),
                            _: 1
                        }), f(o, {onClick: l}, {default: S(() => [K("退出")]), _: 1})]), _: 1
                    })]),
                    default: S(() => [i("div", rs, [i("div", is, [f(Ve, {
                        userId: p.value.id,
                        avatar: p.value.avatar,
                        timestamp: u.value
                    }, null, 8, ["userId", "avatar", "timestamp"])]), i("span", cs, M(p.value.nickname), 1)])]),
                    _: 1
                })])]), i("div", us, [i("div", ds, [f(B(gt), {
                    ref_key: "asideMenuRef",
                    ref: w
                }, null, 512)]), i("div", ps, [f(C, null, {
                    default: S(({Component: I}) => [(v(), Z(Se(I), {
                        ref_key: "routerViewRef",
                        ref: y,
                        onAddFile: x
                    }, null, 544))]), _: 1
                })])]), f(Ut, {
                    ref_key: "uploadAvatarRef",
                    ref: r,
                    onUploadSuccess: z
                }, null, 512), f(Ft, {ref_key: "uploadPasswordRef", ref: c}, null, 512)])
            }
        }
    }), $s = G(hs, [["__scopeId", "data-v-e3863dc4"]]);
export {$s as default};
