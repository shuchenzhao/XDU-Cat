/*
 Navicat Premium Data Transfer

 Source Server         : ZCWzy's MongoDB
 Source Server Type    : MongoDB
 Source Server Version : 40003
 Source Host           : localhost:27017
 Source Schema         : XDUcat

 Target Server Type    : MongoDB
 Target Server Version : 40003
 File Encoding         : 65001

 Date: 07/06/2022 22:37:02
*/


// ----------------------------
// Collection structure for News
// ----------------------------
db.getCollection("News").drop();
db.createCollection("News");

// ----------------------------
// Documents of News
// ----------------------------
db.getCollection("News").insert([ {
    _id: ObjectId("627f1bb603b96d1ccc00447b"),
    title: "欢迎来到西电流浪猫之家",
    date: "2022-5-14",
    description: "欢迎您回家!小主子就已经在门口等着您~"
} ]);
db.getCollection("News").insert([ {
    _id: ObjectId("627f1c5103b96d1ccc00447c"),
    title: "猫猫习性",
    date: "2022-5-14",
    description: "喵喵喜欢空间狭窄的地方,例如房间的角落或者纸盒等,主人可以在家里给猫咪准备猫爬架或者猫窝等属于猫的隐私空间的地方。"
} ]);
db.getCollection("News").insert([ {
    _id: ObjectId("627f1e3203b96d1ccc00447d"),
    title: "猫咪无偿领养",
    date: "2021-09-19",
    description: "西安长安区猫咪无偿领养！超亲人超可爱的小三花！ 超喜欢蹭人呜呜呜，希望在下大雨之前给孩子找个家😭"
} ]);
db.getCollection("News").insert([ {
    _id: ObjectId("627f1e5e03b96d1ccc00447e"),
    title: "在西电，发现美好的意义",
    date: "2022-4-25",
    description: "世事纷纷\r\n\r\n如同彩色的笔墨\r\n\r\n肆意向大地挥洒着丹青\r\n\r\n为饱满的人间绘上了无数生机\r\n\r\n让每一个生灵都有了\r\n\r\n存在的意义和价值\r\n\r\n西电的一切也毫无例外\r\n\r\n那么，就让我们一起来找寻\r\n\r\n校园里我们熟悉事物的意义吧.讲堂上的老师灌溉知识的养分\r\n\r\n课堂上的学生汲取新的营养\r\n\r\n每一本书本，每一串公式\r\n\r\n记录下我们的回忆\r\n\r\n知识在传递\r\n\r\n努力被记录\r\n\r\n最终都将变成丰厚的果实\r\n\r\n书卷飘香\r\n\r\n每一处都充盈着知识的气息\r\n\r\n古褐书柜\r\n\r\n每一本都满载着创作的汗水\r\n\r\n当万物归于沉寂\r\n\r\n这里却笔耕不辍、灯火通明\r\n\r\n当黑夜包围世间\r\n\r\n这里却挑灯苦读\r\n\r\n谋划着明日绚丽的霓虹\r\n\r\n它让理想有了寄托\r\n\r\n让努力有了累积\r\n\r\n让羽翼得到丰满\r\n\r\n让智慧得以传递"
} ]);
db.getCollection("News").insert([ {
    _id: ObjectId("627f1ecc03b96d1ccc00447f"),
    title: "#今天国际猫咪日# ",
    date: "2020-8-9",
    description: "1944年，苏联第二大城市列宁格勒遭遇鼠患，当地博物馆等重要建筑受损，政府随即决定从秋明市运送238只猫帮助消除鼠患。为了纪念猫咪做出的贡献，秋明市特意设立的一个广场，附近还有多座猫咪的雕像."
} ]);
db.getCollection("News").insert([ {
    _id: ObjectId("627f1f5303b96d1ccc004480"),
    title: "【领养】长安区活泼小猫咪 ",
    date: "2019-10-05",
    description: "他是我们学校的流浪猫二代，具体我也不知道几个月大了，能吃猫粮了，最近天天和妈妈蹲在路边等投喂，感觉小猫应该好找领养，冬天快要来了希望他能有一个家。"
} ]);
db.getCollection("News").insert([ {
    _id: ObjectId("627f1f7103b96d1ccc004481"),
    title: "长毛猫掉毛",
    date: "2020-09-11",
    description: "对于长毛猫来说，掉毛的主要原因就是季节以及天气的影响，天气转变会使猫咪的荷尔蒙改变，这种脱毛通常没有办法制止，只能加强梳理，过了换毛的季节就会缓解很多。"
} ]);
db.getCollection("News").insert([ {
    _id: ObjectId("627f1fe903b96d1ccc004482"),
    title: "猫条是什么东西?",
    date: "2022-5-14",
    description: "补水补肉，给猫快乐，一支猫条就能搞定！你家猫爱吃猫条吗？"
} ]);
db.getCollection("News").insert([ {
    _id: ObjectId("627f201d03b96d1ccc004483"),
    title: "西电里有个“动物园”",
    date: "2022-5-14",
    description: "这就是我们可爱的猫猫天团! 我们西电最最萌的小猫咪 你真的不想养它嘛? 猫猫们每天元气满满 只为把最甜蜜的时刻留给你们!"
} ]);
