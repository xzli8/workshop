# Feed 卡片契约（BNTO-8351）

> 原页：https://app.notion.com/p/3c3128bc62c6813eaef7da195b696196 ，最后编辑 2026-09-11，导出 2026-09-18。
> 范围：推荐卡片流接口 `POST /v1/recommend/feed`，一个接口按 `scene` 服务三类页面：`feed`（homepage For You）、`product_closeup`（商品下钻页 feed，已实现）、`collection_lp` / `brand_lp`（collection / brand 下钻页 feed，规划中）。item PLP 页面（`v1/basic/productList*`）不在本接口范围。

## Request

```json
{
  "pageSize": 20,                      // 响应总卡数上限（含插卡）；供给不足时更少。翻页判据只看 nextCursor，不看条数
  "cursor": null,                      // 不带 = 会话重置（刷新/重进）；翻页原样回传 nextCursor，不解析
  "scene": "feed",                     // 缺省 feed；product_closeup / collection_lp / brand_lp
  "anchor": "1835842244101955586",     // 下钻页必填：product_closeup 传商品 id，collection_lp / brand_lp 传 handle；feed 忽略
  "path": ["1835842244101955580"]      // 下钻页可选：上层下钻页的 anchor，根在前；路径去重用
}
```

- 未知 `scene` → 业务码 60003；下钻页缺 `anchor` → 业务码 60002

## Response

```json
{
  "nextCursor": "djF8MTU",    // null = 到底；翻页时原样回传
  "cards": [

    // ── product · s_info ──（feed 内唯一商品样式；id = shopProductId）
    {
      "type": "product",
      "id": "1835842244101955586",
      "layout": { "style": "s_info" },
      "payload": {
        "shopProductId": "1835842244101955586",
        "brand": "Reformation",
        "title": "Juliette Silk Dress",
        "currency": "$",
        "fullPrice": "248",
        "cover": { "url": "https://…", "width": 447, "height": 625, "ratio": "0.71" },
        "hoverCover": { "url": "https://…", "width": 447, "height": 625, "ratio": "0.71" },   // 悬停时切换的第二张图；无 hover 图的商品不下发此键
        "imageSets": [               // 卡片相册，按展示顺序；[0] 恒为 cover 同一张；取图优先级 imageSets > cover；每张比 cover 多一个 type（BNTO-9195）
          { "url": "https://…", "width": 447, "height": 625, "ratio": "0.71", "type": "main" },
          { "url": "https://…", "width": 447, "height": 625, "ratio": "0.71", "type": "view" }
        ],
        "cardBgColor": "#EEECEA",    // 本期固定值（PRD 4.2.1）；按商品取色随 Rec 2.1 / BNTO-8399 上线，字段不变
        "colorId": "Black",
        "isFollow": false,
        "premium": true,
        "aiAction": { "quickButtons": [ { "…": "…" } ], "swap": false },
        "marketingTags": [ { "…": "…" } ],
        "inventoryTags": [ { "…": "…" } ],
        "sensorsAppend": {           // 客户端规则：该卡任何事件把 sensorsAppend 整包平铺合并进事件属性，不解析；除下列投放键外另含商品自带键
          "request_id": "req-7f3a92",
          "exp_group": ["feedBlend-treatment"],    // 无实验分配时不下发
          "card_id": "1835842244101955586",
          "card_type": "product",
          "card_style": "s_info",
          "size_token": "L",
          "span": 1,
          "slot_index": 1            // 会话内槽位序号，跨页累计，刷新重置
        }
      }
    },

    // ── product · s_ymal ──（仅 product_closeup 场景：相似商品段固定 s_info，YMAL 段固定 s_ymal，段内不交替；payload 同 s_info）
    { "type": "product", "id": "1835842244101955587", "layout": { "style": "s_ymal" }, "payload": { "…": "同 s_info" } },

    // ── collection · s_large ──（id = handle；点击进该 handle 的 collection PLP）
    {
      "type": "collection",
      "id": "commuter-outfits",
      "layout": { "style": "s_large" },
      "payload": {
        "handle": "commuter-outfits",
        "title": "Commuter Outfits",
        "cover": { "url": "https://…", "width": 1000, "height": 1250, "ratio": "0.80" },
        "thumbnails": [ { "shopProductId": "…", "cover": { "…": "…" }, "brand": "…", "title": "…", "fullPrice": "…" } ],   // 全量下发（上限 6），客户端按样式截取：大卡 hover 取前 4（BNTO-9048）
        "headerText": null           // 非空即渲染头带；scene 级常量，feed 内恒 null
      }
    },

    // ── collection · s_stack ──（payload 切换为 wrapper；张数 = stacks.length；id = "stk_" + 成员 handle 排序拼接的 md5 前 12 位）
    {
      "type": "collection",
      "id": "stk_8f3a01c2d4e5",
      "layout": { "style": "s_stack" },
      "payload": {
        "headerText": null,
        "stacks": [
          { "handle": "wedding-guest", "title": "Wedding Guest", "cover": { "…": "…" }, "thumbnails": [ { "…": "…" } ] },
          { "handle": "corporate-chic", "title": "Corporate Chic", "cover": { "…": "…" } }
        ]
      }
    },

    // ── collection · s_wide ──（thumbnails 所有样式全量下发，上限 6 张，只取有货单品、最新在前；客户端按样式上限截取——s_wide 6 / s_large hover 4）
    {
      "type": "collection",
      "id": "night-out",
      "layout": { "style": "s_wide" },
      "payload": {
        "handle": "night-out",
        "title": "Night Out",
        "cover": { "url": "https://…", "width": 1440, "height": 1106, "ratio": "1.30" },
        "thumbnails": [ { "shopProductId": "1835842244101955590", "cover": { "…": "…" }, "brand": "Reformation", "title": "Juliette Silk Dress", "fullPrice": "248" } ]
      }
    },

    // ── brand · s_large ──（payload 比 collection 多一个 shopBrandId：进品牌页用 id，进品牌同名 collection PLP 用 handle；字符串下发，数值超出 JS 安全整数范围）
    {
      "type": "brand",
      "id": "viavia",
      "layout": { "style": "s_large" },
      "payload": {
        "handle": "viavia",
        "shopBrandId": "1691239759832875010",
        "title": "VIAVIA",
        "cover": { "url": "https://…", "width": 1770, "height": 2213, "ratio": "0.79" },
        "thumbnails": [ { "shopProductId": "…", "cover": { "…": "…" }, "brand": "…", "title": "…", "fullPrice": "…" } ],
        "headerText": null
      }
    },

    // ── brand · s_stack ──
    {
      "type": "brand",
      "id": "stk_cd0cdb43a0fc",
      "layout": { "style": "s_stack" },
      "payload": {
        "headerText": null,
        "stacks": [ { "handle": "4emily", "shopBrandId": "1691239759832875011", "title": "4emily", "cover": { "…": "…" } } ]
      }
    },

    // ── brand · s_wide ──
    {
      "type": "brand",
      "id": "4emily",
      "layout": { "style": "s_wide" },
      "payload": {
        "handle": "4emily",
        "shopBrandId": "1691239759832875011",
        "title": "4emily",
        "cover": { "…": "…" },
        "thumbnails": [ { "shopProductId": "…", "cover": { "…": "…" }, "brand": "…", "title": "…", "fullPrice": "…" } ]
      }
    },

    // ── survey · s_survey_size ──（size 题，纯文字选项；一卡一题一投放，id = deliveryId；span=2）
    {
      "type": "survey",
      "id": "9f3a1c2e-7b4d-4e8a-9c01-2d5f6a7b8c90",
      "layout": { "style": "s_survey_size" },
      "payload": {
        "deliveryId": "9f3a1c2e-7b4d-4e8a-9c01-2d5f6a7b8c90",    // 本次投放 id；SAVE / skip 回传
        "questionType": "size",
        "title": "Clothing Size",
        "subtitle": "Sizing can vary by brand. Select multiple sizes you wear to see more options.",
        "options": [ { "optionId": "size_s", "label": "S", "usRange": "US 2-4", "wRange": "W 26-27" } ],
        "doneFeedback": { "title": "Refreshing your feed…", "subtitle": "A little different might look really good.", "copyId": 1 }   // 缺省则客户端回落默认文案
      }
    },

    // ── survey · s_survey_grid ──（style / occasion 宫格题，选项带图；服务端已完成选项与配图的随机抽取；span=2）
    {
      "type": "survey",
      "id": "c1d2e3f4-5a6b-4c7d-8e9f-0a1b2c3d4e5f",
      "layout": { "style": "s_survey_grid" },
      "payload": {
        "deliveryId": "c1d2e3f4-5a6b-4c7d-8e9f-0a1b2c3d4e5f",
        "questionType": "style",
        "title": "What's your current style mood?",
        "subtitle": "Select all that apply",
        "options": [
          { "optionId": "style_classic", "label": "Classic", "image": { "url": "https://…", "width": 600, "height": 600, "ratio": "1.00" }, "imageId": "10231" }   // imageId 字符串；图片归因键，埋点 option_image_id 直接取
        ],
        "doneFeedback": { "…": "同 s_survey_size" }
      }
    }
  ]
}
```

## 解析规则

- payload 形状：`layout.style == "s_stack"` → wrapper（headerText + stacks），否则 → 该 type 的单体 schema；单体 schema 只随 type 变，样式只决定可选字段是否填充（`thumbnails` 所有样式均下发、上限 6，客户端按样式截取；`headerText` 仅 s_large）；联排张数 = `stacks.length`
- 样式选择门槛（服务端，PRD 5.6.4 / 5.6.5，修订 9.8，BNTO-9048）：brand / collection 有货单品 ≥2 才进池，可出 s_large / s_stack；≥3 才可出 s_wide，<3 降级为大卡。客户端不做任何「张数不足整卡不渲染」判断，缩略图按下发张数渲染、样式选择按有货数判，两个数分开
- 头带（`headerText`）：非空即渲染。文案是 **scene 级常量**，不随内容 / 卡型 / 位置变化——`feed` 恒 `null`（不渲染），`product_closeup` 固定 `"YOU MAY ALSO LIKE"`，`collection_lp` / `brand_lp` 上线时各自定值。下发范围：s_large（单体 payload）与 s_stack（wrapper，头带属于整组、不落在成员上），s_wide 不带（BNTO-8962）
- 卡 id：product = shopProductId；collection / brand = handle；s_stack = `stk_` + 成员 handle 排序拼接的 md5 前 12 位（同一组合任意顺序恒定）；survey = deliveryId；去重与成员级点击归因用 stacks 内的 handle，不用 stk_ id
- 未知 `(type, layout.style)`：静默跳过该卡并上报，不得渲染空白或崩溃
- span=2 卡（s_wide / survey）渲染前实测校验（PRD 5.6.6，8.26 三端统一）：实测两列高度差 > 阈值（60pt 基准，按列宽等比）时**降级落位不丢卡**——落到高度差最小的相邻列对（双列端即唯一列对），上方留白，打降级标记（埋点 `card_downgraded` 随 V3.0.8）；不跳卡、不替换
- survey 卡：Question / Option 结构与 Feed Survey 弹窗前端接口定义共用；卡内 SAVE 调 `POST /v1/feed/survey/submit`、右上角 skip 调 `POST /v1/feed/survey/dismiss`，均带 `form=FEED_CARD` 并回传 `payload.deliveryId`；skip 恒可用，无开关字段
- survey 卡落位（仅 feed 场景、登录用户）：不进首屏——服务端按双列口径不入前 5 槽（序列跨端一致）；Web 多列端另按**客户端落位约束**执行：渲染时 survey 若落进当前列数的前 ⌊5n/2⌋ 视觉槽，顺延到窗口后第一个合法位，resize 重排时按新 n 重新执行；同一位置优先于 brand / collection 卡；两张 survey 间隔 ≥20 槽；单 session ≤3 张、必为不同题，题序 size → style → occasion；与 s_wide 共享"连续 12 槽内跨双列 ≤1 张"；两列不齐时前一格先落一组 brand / collection 联排补齐，其后 1 格重建错落；出题与频控（当日 3 次、同题当日 1 次、skip 7 天冷却）由 survey 服务侧执行
- survey 提交后完成态卡（s_survey_done）由客户端本地渲染；完成态结束后下方卡片自动上移填补原位置，不插入任何新卡、不请求接口（8.27 终版口径）
- 本期埋点仅商品卡：`payload.sensorsAppend` 只在 product 卡下发
- 场景差异：下钻页（product_closeup / collection_lp / brand_lp）不下发 s_wide、不投 survey 卡；s_ymal 仅 product_closeup 下发
- product_closeup 内容：相似商品段 → YMAL 段（仅登录用户有 YMAL 段），两段顺序展示不交叉、无段标题；不含 anchor 商品和 path 上的商品
- product_closeup 插卡：brand / collection 卡只落在三个窗口（PRD 4.4，1-based 槽位）——第 3–5 槽大卡 1 张、第 9–11 槽联排 1 组、第 15–17 槽大卡或联排 1 张，第 20 槽后不插；且只在相似商品段内，YMAL 段不插；不下发 anchor 商品自己的 brand 与所属 collection

## 样式常量表（契约的一部分，两端实现各自对照）

尺寸为 App 375pt 视口 / 列宽 175.5pt 基准；Web 端按列宽等比缩放。表中为内容高度基准值，**不含头带**；带头带的卡总高 = 头带高度 + 该基准值，两段分开校验。

| style | 卡型 | 占列 | 高度档 | 高度 pt |
|---|---|---|---|---|
| s_info | product | 1 | L | 318.7 |
| s_ymal | product（仅 product_closeup） | 1 | M | 245.7 |
| s_large | collection / brand | 1 | S | 219.4 |
| s_stack | collection / brand | 1 | XS / S / L | 103.7 × N + 8 × (N−1)，N = stacks.length |
| s_wide | collection / brand | 2 | W | 275.6 |
| s_survey_size | survey | 2 | W | 329 |
| s_survey_grid | survey | 2 | W | 329 |
