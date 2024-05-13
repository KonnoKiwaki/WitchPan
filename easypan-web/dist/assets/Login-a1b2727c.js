import {
    d as O,
    o as u,
    c as m,
    a,
    u as J,
    b as W,
    r as g,
    e as F,
    f as Q,
    g as k,
    h as o,
    w as l,
    E as S,
    i as p,
    j as b,
    k as c,
    l as f,
    m as X,
    t as Y,
    p as Z,
    n as ee,
    q as oe,
    V as le,
    P as te
} from "./index-ea894644.js";
import {_ as se} from "./Dialog-65b9ab6b.js";
import {V as A} from "./Verify-bd74e60f.js";
import {s as ae, L as R, r as re, l as ie, a as ne} from "./user-56afb0b3.js";
import {_ as de} from "./_plugin-vue_export-helper-c27b6911.js";
import "./setting-8d83341a.js";

const ue = {
        xmlns: "http://www.w3.org/2000/svg",
        "xmlns:xlink": "http://www.w3.org/1999/xlink",
        viewBox: "0 0 1024 1024"
    }, ce = a("path", {
        d: "M928 160H96c-17.7 0-32 14.3-32 32v640c0 17.7 14.3 32 32 32h832c17.7 0 32-14.3 32-32V192c0-17.7-14.3-32-32-32zm-40 110.8V792H136V270.8l-27.6-21.5l39.3-50.5l42.8 33.3h643.1l42.8-33.3l39.3 50.5l-27.7 21.5zM833.6 232L512 482L190.4 232l-42.8-33.3l-39.3 50.5l27.6 21.5l341.6 265.6a55.99 55.99 0 0 0 68.7 0L888 270.8l27.6-21.5l-39.3-50.5l-42.7 33.2z",
        fill: "currentColor"
    }, null, -1), me = [ce], pe = O({
        name: "MailOutlined", render: function (C, y) {
            return u(), m("svg", ue, me)
        }
    }), fe = {xmlns: "http://www.w3.org/2000/svg", "xmlns:xlink": "http://www.w3.org/1999/xlink", viewBox: "0 0 1024 1024"},
    _e = a("path", {
        d: "M866.9 169.9L527.1 54.1C523 52.7 517.5 52 512 52s-11 .7-15.1 2.1L157.1 169.9c-8.3 2.8-15.1 12.4-15.1 21.2v482.4c0 8.8 5.7 20.4 12.6 25.9L499.3 968c3.5 2.7 8 4.1 12.6 4.1s9.2-1.4 12.6-4.1l344.7-268.6c6.9-5.4 12.6-17 12.6-25.9V191.1c.2-8.8-6.6-18.3-14.9-21.2zM810 654.3L512 886.5L214 654.3V226.7l298-101.6l298 101.6v427.6zm-405.8-201c-3-4.1-7.8-6.6-13-6.6H336c-6.5 0-10.3 7.4-6.5 12.7l126.4 174a16.1 16.1 0 0 0 26 0l212.6-292.7c3.8-5.3 0-12.7-6.5-12.7h-55.2c-5.1 0-10 2.5-13 6.6L468.9 542.4l-64.7-89.1z",
        fill: "currentColor"
    }, null, -1), ve = [_e], U = O({
        name: "SafetyCertificateOutlined", render: function (C, y) {
            return u(), m("svg", fe, ve)
        }
    }), ge = {xmlns: "http://www.w3.org/2000/svg", "xmlns:xlink": "http://www.w3.org/1999/xlink", viewBox: "0 0 1024 1024"},
    we = a("path", {
        d: "M858.5 763.6a374 374 0 0 0-80.6-119.5a375.63 375.63 0 0 0-119.5-80.6c-.4-.2-.8-.3-1.2-.5C719.5 518 760 444.7 760 362c0-137-111-248-248-248S264 225 264 362c0 82.7 40.5 156 102.8 201.1c-.4.2-.8.3-1.2.5c-44.8 18.9-85 46-119.5 80.6a375.63 375.63 0 0 0-80.6 119.5A371.7 371.7 0 0 0 136 901.8a8 8 0 0 0 8 8.2h60c4.4 0 7.9-3.5 8-7.8c2-77.2 33-149.5 87.8-204.3c56.7-56.7 132-87.9 212.2-87.9s155.5 31.2 212.2 87.9C779 752.7 810 825 812 902.2c.1 4.4 3.6 7.8 8 7.8h60a8 8 0 0 0 8-8.2c-1-47.8-10.9-94.3-29.5-138.2zM512 534c-45.9 0-89.1-17.9-121.6-50.4S340 407.9 340 362c0-45.9 17.9-89.1 50.4-121.6S466.1 190 512 190s89.1 17.9 121.6 50.4S684 316.1 684 362c0 45.9-17.9 89.1-50.4 121.6S557.9 534 512 534z",
        fill: "currentColor"
    }, null, -1), he = [we], ke = O({
        name: "UserOutlined", render: function (C, y) {
            return u(), m("svg", ge, he)
        }
    });
const P = _ => (Z("data-v-bcf00753"), _ = _(), ee(), _), Ce = {class: "login-body"},
    ye = P(() => a("div", {class: "bg"}, null, -1)), xe = {class: "login-panel"},
    Ve = P(() => a("div", {class: "login-title"}, "魔女云盘", -1)), be = {key: 1}, Me = {class: "send-email-panel"},
    Le = P(() => a("div", null, [a("p", null, "1、在垃圾箱中查找邮箱验证码"), a("p", null, "2、在邮箱中头像->设置->反垃圾->白名单->设置邮箱地址白名单"), a("p", null, "3、将邮箱【2627.311935@qq.com】添加到白名单")], -1)),
    Se = {class: "check-code-panel"}, Pe = ["src"], $e = {class: "no-account"}, qe = {class: "no-account"},
    Re = {class: "no-account"}, Ue = {key: 0}, Oe = {key: 1}, Ee = {key: 2}, ze = {key: 5, class: "login-btn-qq"},
    Ne = P(() => a("img", {src: "https://passport.baidu.com/passApi/img/bd-acc-qzone.png"}, null, -1)),
    Be = {class: "check-code-panel"}, Ie = ["src"], Fe = {
        __name: "Login", setup(_) {
            const C = J(), y = W(), D = te.BASE_LOGIN_NAME, $ = {checkCode: "/api/checkCode"}, T = (d, e, n) => {
                e !== s.value.registerPassword ? n(new Error(d.message)) : n()
            }, r = g(1), M = g(), s = g({email: ""}), E = F({
                email: [{required: !0, message: "请输入邮箱", trigger: "blur"}, {
                    validator: A.email,
                    message: "请输入正确的邮箱",
                    trigger: "blur"
                }],
                password: [{required: !0, message: "请输入密码", trigger: "blur"}],
                nickname: [{required: !0, message: "请输入昵称", trigger: "blur"}],
                registerPassword: [{required: !0, message: "请输入密码", trigger: "blur"}, {
                    validator: A.password,
                    message: "密码只能是数字, 字母, 特殊字符8-18位",
                    trigger: "blur"
                }],
                reRegisterPassword: [{required: !0, message: "请再次输入密码", trigger: "blur"}, {
                    validator: T,
                    message: "两次密码不一致",
                    trigger: "blur"
                }],
                checkCode: [{required: !0, message: "请输入图片验证码"}],
                emailCode: [{required: !0, message: "请输入邮箱验证码"}]
            }), x = g({}), q = g(), w = F({
                show: !1, title: "发送邮箱验证码", buttons: [{
                    type: "primary", text: "发送验证码", click: d => {
                        K()
                    }
                }]
            }), z = g($.checkCode), N = g($.checkCode);
            Q(() => {
                v(1), s.value = {email: "2627311935@qq.com", password: "Song@Wu.code06"}
            });
            const v = d => {
                r.value = d, H()
            };

            function h(d) {
                const e = $.checkCode + "?type=" + d + "&time=" + new Date().getTime();
                d == 0 ? z.value = e : N.value = e
            }

            function j() {
                M.value.validateField("email", d => {
                    d && (w.show = !0, oe(() => {
                        h(1), q.value.resetFields(), x.value = {email: s.value.email}
                    }))
                })
            }

            const H = () => {
                h(0), M.value.resetFields(), s.value = {}
            }, K = () => {
                q.value.validate(async d => {
                    if (!d) return;
                    const e = Object.assign({}, x.value);
                    e.type = r.value == 0 ? 0 : 1;
                    try {
                        await ae(e), S.success("验证码发送成功，请登陆邮箱查看"), w.show = !1
                    } catch {
                        h(1)
                    }
                })
            }, B = () => {
                M.value.validate(async d => {
                    var n;
                    if (!d) return;
                    let e = {};
                    Object.assign(e, s.value), (r.value == 0 || r.value == 2) && (e.password = e.reRegisterPassword, delete e.reRegisterPassword, delete e.registerPassword);
                    try {
                        switch (r.value) {
                            case 0: {
                                await ne(e), S.success("注册成功，请登陆"), v(1);
                                break
                            }
                            case 1: {
                                const i = await ie(e);
                                S.success("登陆成功"), le.set("userInfo", JSON.stringify(i), 0);
                                const L = decodeURIComponent(((n = C.query) == null ? void 0 : n.redirect) || "/");
                                C.name == D ? y.replace("/") : y.replace(L);
                                break
                            }
                            case 2: {
                                await re(e), S.success("密码重置成功，请登陆"), v(1);
                                break
                            }
                        }
                    } catch {
                        h(0)
                    }
                })
            };
            return (d, e) => {
                const n = k("el-input"), i = k("el-form-item"), L = k("el-button"), V = k("el-link"), G = k("el-popover"),
                    I = k("el-form");
                return u(), m("div", Ce, [ye, a("div", xe, [o(I, {
                    model: s.value,
                    rules: E,
                    ref_key: "loginFormRef",
                    ref: M,
                    class: "login-register"
                }, {
                    default: l(() => [Ve, o(i, {prop: "email"}, {
                        default: l(() => [o(n, {
                            clearable: "",
                            placeholder: "请输入邮箱",
                            modelValue: s.value.email,
                            "onUpdate:modelValue": e[0] || (e[0] = t => s.value.email = t),
                            modelModifiers: {trim: !0},
                            "prefix-icon": p(pe),
                            maxLength: "150"
                        }, null, 8, ["modelValue", "prefix-icon"])]), _: 1
                    }), r.value == 1 ? (u(), b(i, {key: 0, prop: "password"}, {
                        default: l(() => [o(n, {
                            "show-password": "",
                            type: "password",
                            placeholder: "请输入密码",
                            modelValue: s.value.password,
                            "onUpdate:modelValue": e[1] || (e[1] = t => s.value.password = t),
                            modelModifiers: {trim: !0},
                            "prefix-icon": p(R),
                            maxLength: "20"
                        }, null, 8, ["modelValue", "prefix-icon"])]), _: 1
                    })) : c("", !0), r.value == 0 || r.value == 2 ? (u(), m("div", be, [o(i, {prop: "emailCode"}, {
                        default: l(() => [a("div", Me, [o(n, {
                            placeholder: "请输入邮箱验证码",
                            modelValue: s.value.emailCode,
                            "onUpdate:modelValue": e[2] || (e[2] = t => s.value.emailCode = t),
                            modelModifiers: {trim: !0},
                            "prefix-icon": p(U)
                        }, null, 8, ["modelValue", "prefix-icon"]), o(L, {
                            class: "send-mail-btn",
                            type: "primary",
                            onClick: j
                        }, {default: l(() => [f("获取邮箱验证码")]), _: 1})]), o(G, {
                            placement: "left",
                            width: 500,
                            trigger: "click"
                        }, {
                            reference: l(() => [o(V, {
                                type: "primary",
                                underline: !1
                            }, {default: l(() => [f("未收到邮箱验证码？")]), _: 1})]), default: l(() => [Le]), _: 1
                        })]), _: 1
                    }), r.value == 0 ? (u(), b(i, {
                        key: 0,
                        prop: "nickname"
                    }, {
                        default: l(() => [o(n, {
                            placeholder: "请输入昵称",
                            modelValue: s.value.nickname,
                            "onUpdate:modelValue": e[3] || (e[3] = t => s.value.nickname = t),
                            modelModifiers: {trim: !0},
                            "prefix-icon": p(ke),
                            maxLength: "20"
                        }, null, 8, ["modelValue", "prefix-icon"])]), _: 1
                    })) : c("", !0), o(i, {prop: "registerPassword"}, {
                        default: l(() => [o(n, {
                            "show-password": "",
                            type: "password",
                            placeholder: "请输入密码",
                            modelValue: s.value.registerPassword,
                            "onUpdate:modelValue": e[4] || (e[4] = t => s.value.registerPassword = t),
                            modelModifiers: {trim: !0},
                            "prefix-icon": p(R),
                            maxLength: "20"
                        }, null, 8, ["modelValue", "prefix-icon"])]), _: 1
                    }), o(i, {prop: "reRegisterPassword"}, {
                        default: l(() => [o(n, {
                            "show-password": "",
                            type: "password",
                            placeholder: "请再次输入密码",
                            modelValue: s.value.reRegisterPassword,
                            "onUpdate:modelValue": e[5] || (e[5] = t => s.value.reRegisterPassword = t),
                            modelModifiers: {trim: !0},
                            "prefix-icon": p(R),
                            maxLength: "20"
                        }, null, 8, ["modelValue", "prefix-icon"])]), _: 1
                    })])) : c("", !0), o(i, {prop: "checkCode"}, {
                        default: l(() => [a("div", Se, [o(n, {
                            placeholder: "请输入验证码",
                            modelValue: s.value.checkCode,
                            "onUpdate:modelValue": e[6] || (e[6] = t => s.value.checkCode = t),
                            modelModifiers: {trim: !0},
                            "prefix-icon": p(U),
                            onKeyup: X(B, ["enter"])
                        }, null, 8, ["modelValue", "prefix-icon", "onKeyup"]), a("img", {
                            src: z.value,
                            class: "check-code",
                            onClick: e[7] || (e[7] = t => h(0))
                        }, null, 8, Pe)])]), _: 1
                    }), r.value == 1 ? (u(), b(i, {key: 2}, {
                        default: l(() => [a("div", $e, [o(V, {
                            type: "primary",
                            underline: !1,
                            onClick: e[8] || (e[8] = t => v(2))
                        }, {default: l(() => [f("忘记密码？")]), _: 1}), o(V, {
                            type: "primary",
                            underline: !1,
                            onClick: e[9] || (e[9] = t => v(0))
                        }, {default: l(() => [f("没有账号？")]), _: 1})])]), _: 1
                    })) : c("", !0), r.value == 2 ? (u(), b(i, {key: 3}, {
                        default: l(() => [a("div", qe, [o(V, {
                            type: "primary",
                            underline: !1,
                            onClick: e[10] || (e[10] = t => v(1))
                        }, {default: l(() => [f("去登陆")]), _: 1})])]), _: 1
                    })) : c("", !0), r.value == 0 ? (u(), b(i, {key: 4}, {
                        default: l(() => [a("div", Re, [o(V, {
                            type: "primary",
                            underline: !1,
                            onClick: e[11] || (e[11] = t => v(1))
                        }, {default: l(() => [f("已有账号？")]), _: 1})])]), _: 1
                    })) : c("", !0), o(i, null, {
                        default: l(() => [o(L, {
                            type: "primary",
                            style: {width: "100%"},
                            onClick: B
                        }, {
                            default: l(() => [r.value == 0 ? (u(), m("span", Ue, "注册")) : c("", !0), r.value == 1 ? (u(), m("span", Oe, "登陆")) : c("", !0), r.value == 2 ? (u(), m("span", Ee, "重置密码")) : c("", !0)]),
                            _: 1
                        })]), _: 1
                    }), r.value == 1 ? (u(), m("div", ze, [f(" 快捷登陆 "), Ne])) : c("", !0)]), _: 1
                }, 8, ["model", "rules"])]), o(se, {
                    show: w.show,
                    title: w.title,
                    buttons: w.buttons,
                    width: "500px",
                    showCancel: !1,
                    onClose: e[14] || (e[14] = t => w.show = !1)
                }, {
                    default: l(() => [o(I, {
                        model: x.value,
                        rules: E,
                        "label-width": "80px",
                        ref_key: "formRef4SendMailCode",
                        ref: q
                    }, {
                        default: l(() => [o(i, {label: "邮箱"}, {
                            default: l(() => [f(Y(s.value.email), 1)]),
                            _: 1
                        }), o(i, {
                            label: "验证码",
                            prop: "checkCode"
                        }, {
                            default: l(() => [a("div", Be, [o(n, {
                                placeholder: "请输入验证码",
                                modelValue: x.value.checkCode,
                                "onUpdate:modelValue": e[12] || (e[12] = t => x.value.checkCode = t),
                                modelModifiers: {trim: !0},
                                "prefix-icon": p(U)
                            }, null, 8, ["modelValue", "prefix-icon"]), a("img", {
                                src: N.value,
                                class: "check-code",
                                onClick: e[13] || (e[13] = t => h(1))
                            }, null, 8, Ie)])]), _: 1
                        })]), _: 1
                    }, 8, ["model", "rules"])]), _: 1
                }, 8, ["show", "title", "buttons"])])
            }
        }
    }, Ge = de(Fe, [["__scopeId", "data-v-bcf00753"]]);
export {Ge as default};
