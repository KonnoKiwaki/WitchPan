import {
    r as u,
    e as I,
    g as r,
    o as _,
    c as m,
    a as n,
    h as s,
    w as l,
    m as K,
    i as v,
    l as f,
    t as h,
    k as y,
    K as k
} from "./index-ea894644.js";
import {B as N} from "./Table-3fbda55d.js";
import {A as U} from "./Avatar-470ce3bd.js";
import {s as x} from "./setting-8d83341a.js";
import {_ as A} from "./_plugin-vue_export-helper-c27b6911.js";

const L = [{label: "昵称", prop: "nickname"}, {
    label: "头像",
    prop: "avatar",
    scopedSlots: "avatar",
    width: 80
}, {label: "邮箱", prop: "email", ellipsis: !0}, {
    label: "空间使用",
    prop: "space",
    scopedSlots: "space",
    ellipsis: !0
}, {label: "加入时间", prop: "createTime", ellipsis: !0}, {
    label: "最后登陆时间",
    prop: "lastLoginTime",
    ellipsis: !0
}, {label: "状态", prop: "status", scopedSlots: "status", width: 80}, {
    label: "操作",
    prop: "op",
    scopedSlots: "op",
    width: 150
}];
const $ = {class: "main-user"}, D = {class: "main-header"}, E = {class: "actions"}, M = {class: "select"},
    j = {class: "search"}, F = {class: "main-body"}, O = {class: "file-list"}, P = {key: 0, style: {color: "#529b2e"}},
    G = {key: 1, style: {color: "#f56c62"}}, H = {name: "UserSetting"}, J = Object.assign(H, {
        setup(Q) {
            const S = u(), g = u([]), p = u(L), i = I({select: "", filename: ""}), w = a => ({...a, ...i}, {
                pages: 2,
                records: [{
                    id: "1",
                    avatar: "test",
                    nickname: "test",
                    email: "test@qq.com",
                    useSpace: 1024e4,
                    totalSpace: 2048e4,
                    createTime: "2023-04-20 09:57",
                    lastLoginTime: "2023-04-20 09:57",
                    status: "1"
                }, {
                    id: "2",
                    avatar: "swcode",
                    nickname: "swcode",
                    email: "swcode@qq.com",
                    useSpace: 1024e4,
                    totalSpace: 2048e4,
                    createTime: "2023-07-02 20:24",
                    lastLoginTime: "2023-07-02 20:24",
                    status: "0"
                }]
            }), T = a => {
                a.length > 0 ? p.value[0].label = `已选中${a.length}个用户` : p.value[0].label = "昵称";
                const c = a.map(o => (o.id = o.userId + "_" + o.id, o));
                g.value = c
            }, C = a => {
            }, V = a => {
            }, d = () => {
            };
            return (a, c) => {
                const o = r("el-option"), q = r("el-select"), B = r("el-input"), b = r("el-button"), R = r("el-divider");
                return _(), m("div", $, [n("div", D, [n("div", E, [n("div", M, [s(q, {
                    modelValue: i.select,
                    "onUpdate:modelValue": c[0] || (c[0] = t => i.select = t),
                    class: "status-select",
                    placeholder: "状态",
                    teleported: !1,
                    style: {width: "80px"}
                }, {
                    default: l(() => [s(o, {label: "启用", value: "1"}), s(o, {label: "禁用", value: "0"})]),
                    _: 1
                }, 8, ["modelValue"])]), n("div", j, [s(B, {
                    modelValue: i.filename,
                    "onUpdate:modelValue": c[1] || (c[1] = t => i.filename = t),
                    modelModifiers: {trim: !0},
                    size: "small",
                    clearable: "",
                    placeholder: "搜索我的文件",
                    "input-style": "height: 32px",
                    onKeyup: K(d, ["enter"]),
                    onClear: d,
                    class: "input-with-select"
                }, {
                    suffix: l(() => [n("span", {class: "text", onClick: d}, "搜索")]),
                    _: 1
                }, 8, ["modelValue", "onKeyup"])])])]), n("div", F, [n("div", O, [s(v(N), {
                    ref_key: "userTableRef",
                    ref: S,
                    columns: p.value,
                    "row-key": t => t.id,
                    request: w,
                    loading: !0,
                    onSelectChange: T
                }, {
                    avatar: l(({index: t, row: e}) => [s(U, {
                        userId: e.id,
                        avatar: e.qqAvatar
                    }, null, 8, ["userId", "avatar"])]),
                    space: l(({index: t, row: e}) => [f(h(v(x)(e.useSpace)) + " / " + h(v(x)(e.totalSpace)), 1)]),
                    status: l(({
                                   index: t,
                                   row: e
                               }) => [e.status == 1 ? (_(), m("span", P, "启用")) : y("", !0), e.status == 0 ? (_(), m("span", G, "禁用")) : y("", !0)]),
                    op: l(({index: t, row: e}) => [s(b, {
                        link: "",
                        type: "primary",
                        style: {"font-size": "12px"},
                        onClick: k(z => C(e), ["stop"])
                    }, {
                        default: l(() => [f("分配空间")]),
                        _: 2
                    }, 1032, ["onClick"]), s(R, {direction: "vertical"}), s(b, {
                        link: "",
                        type: e.status == 0 ? "success" : "danger",
                        style: {"font-size": "12px"},
                        onClick: k(z => V(e), ["stop"])
                    }, {default: l(() => [f(h(e.status == 0 ? "启用" : "禁用"), 1)]), _: 2}, 1032, ["type", "onClick"])]),
                    _: 1
                }, 8, ["columns", "row-key"])])])])
            }
        }
    }), te = A(J, [["__scopeId", "data-v-1a7d6cac"]]);
export {te as default};
