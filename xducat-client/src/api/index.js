// 统一进行api管理
import requests from "./requests";

// cat相关api
export const findAllCat = () => {
    return requests({
        url: `http://localhost:8888/cat/findAll`,
        method: "get",
    });
};

export const findCatByColor = color => {
    return requests({
        url: `http://localhost:8888/cat/findCatByColor/${color}`,
        method: "post",
    });
};

export const modifyCat = (id, location) => {
    return requests({
        url: `http://localhost:8888/cat/modify/${id}/${location}`,
        method: "post",
    });
};

export const upload = img => {
    return requests({
        url: `http://localhost:8888/upload/${img}`,
        method: "post",
    });
};

export const insertCat = (location, color, img) => {
    return requests({
        url: `http://localhost:8888/cat/insert/${location}/${color}/${img}`,
        method: "post",
    });
};

// user相关api
export const managerLogin = (username, password) => {
    return requests({
        url: `http://localhost:8888/user/login/${username}/${password}`,
        method: "post",
    });
};

export const addManager = (username, password) => {
    return requests({
        url: `http://localhost:8888/user/addManager/${username}/${password}`,
        method: "post",
    });
};

export const modifyPassword = (username, expassword, nowpassword) => {
    return requests({
        url: `http://localhost:8888/user/modifyPassword/${username}/${expassword}/${nowpassword}`,
        method: "post",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
    });
};

// comment相关api
export const findCommentByCatId = id => {
    return requests({
        url: `http://localhost:8888/comment/findCommentByCatId/${id}`,
        method: "post",
    });
};

export const insertComment = (id, comment, name) => {
    return requests({
        url: `http://localhost:8888/comment/insertComment/${id}/${comment}/${name}`,
        method: "post",
    });
};

export const deleteComment = (id, commentId) => {
    return requests({
        url: `http://localhost:8888/comment/deleteComment/${id}/${commentId}`,
        method: "post",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
    });
};

export const findNews = () => {
    return requests({
        url: 'http://localhost:8888/news/findAll',
        method:'get'
    });
};