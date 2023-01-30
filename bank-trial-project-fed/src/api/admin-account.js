import axios from "axios";
import { useState } from "react";


const http = "http://localhost:8080/api/adminAccount/";
const [allAccounts, setAllAccounts] = useState([]);

export const openAccount = (account) => {

    axios.post(http+"openAccount", account).then(res => {
        swal({
            title: "Success!",
            text: "Account Successfully Created",
            icon: "success",
            button: "Ok",
        });
    }).catch(err => {
        console.log(err);
    });
}

export const getAccounts = () => {
    axios.get(http+"getAccounts", data).then(res => {
        setAllAccounts(res.data);
    }).catch(err => {
        console.log(err);
    });
}

export const closeAccount = (account) => {
    axios.post(http+"closeAccount", account).then(res => {
        swal({
            title: "Success!",
            text: "Account Successfully Deleted",
            icon: "success",
            button: "Ok",
        });
    }).catch(err => {
        console.log(err);
    });
}