import React, {useEffect, useState} from 'react'
import styles from "./DisplayAllAccounts.module.css"
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Checkbox from '@mui/material/Checkbox';
import DashboardIcon from '@mui/icons-material/Dashboard';
import { IconButton, Button } from '@mui/material';
import { useSelector, useDispatch } from 'react-redux';
import { getAllClient, updateClient, archiveClient, deleteClient } from '../../../../redux-actions/adminClientActions';
import { useNavigate } from 'react-router-dom';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import Swal from 'sweetalert2';
function DisplayAllAccounts() {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [moreButtonIsClicked, setMoreButtonIsClicked] = useState(false);
    const [changesMade, setChangesMade] = useState(false);
    const [count, setCount] = useState(0);
    const [index, setIndex] = useState(null)
    const accounts = useSelector((state) => state.getAllAccount);
    const {allAccounts} = accounts;
    useEffect(()=>{
        console.log(allAccounts)
    }, [allAccounts])
    useEffect(() =>{
        dispatch({type: "getAllAccountsReducer"},getAllClient(dispatch));
    }, [])
    const handleUpdate = () => {
    }
    const handleArchive = (account) => {
        setCount(count+1)
        var archiveStatus = account.isArchived? "unarchive" : "archive";
        Swal.fire({
            title: `Are you sure you want to ${archiveStatus} ${account.fullName}?`,
            text: null,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'Cancel'
        }).then((result) => {
            if (result.value) {
                console.log(account.clientId)
                dispatch({type: "archiveClientReducer"}, archiveClient(account.clientId, dispatch));
                setMoreButtonIsClicked(false)
                setChangesMade(!changesMade)
            }else{
                setMoreButtonIsClicked(false)
            }
        });
    }

    const handleDelete = (account) => {
        Swal.fire({
            title: `Are you sure you want to delete ${account.fullName}'s account?`,
            text: null,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'Cancel'
        }).then((result) => {
            if (result.value) {
                console.log(account.clientId)
                dispatch({type: "archiveClientReducer"}, deleteClient(account.clientId, dispatch));
                setMoreButtonIsClicked(false)
                setChangesMade(!changesMade)
            }else{
                setMoreButtonIsClicked(false)
            }
        });
    }
    function Settings(props){
        return  (
            <td id={props.account.clientId} className={styles["settings-container"]}>
                <div className={styles["button-container"]}><Button size="small" variant="contained" onClick={()=>{handleUpdate(props.account)}}>Update</Button></div>
                <div className={styles["button-container"]}><Button size="small" variant="contained" onClick={()=>{handleArchive(props.account)}}>{props.account.isArchived? "Unarchive":"Archive"}</Button></div>
                <div className={styles["button-container"]}><Button size="small" variant="contained" onClick={()=>{handleDelete(props.account)}}>Delete</Button></div>
            </td>
        )
    }
    return (
        <div className={styles["main-container"]}>
            <div className={styles["dashboard-icon-container"]} onClick={()=>navigate("/adminDashBoard")}>
                <IconButton aria-label="dashboard" color='primary'>
                    <DashboardIcon sx={ {fontSize: 30} }/>
                </IconButton>
            </div>
            <div className={styles["table-container-"]}>
                <TableContainer component={Paper}>
                    <Table sx={{ maxWidth: 1050 }} aria-label="simple table">
                        <TableHead>
                        <TableRow>
                            <TableCell>Fullname</TableCell>
                            <TableCell align="right">Address</TableCell>
                            <TableCell align="right">Email</TableCell>
                            <TableCell align="right">Birthdate</TableCell>
                            <TableCell align="right">Admin</TableCell>
                            <TableCell align="right">Archived</TableCell>
                            <TableCell align="right"></TableCell>
                        </TableRow>
                        </TableHead>
                        <TableBody className={styles["account-container"]}>
                            {allAccounts?.map((account, key) => {
                                return (
                                    <TableRow
                                        key={account.clientId}
                                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                                        <TableCell component="th" scope="row">{account.fullName}</TableCell>
                                        <TableCell align="right">{account.address}</TableCell>
                                        <TableCell align="right">{account.email}</TableCell>
                                        <TableCell align="right">{account.birthDate}</TableCell>
                                        <TableCell align="right">{account.isAdmin? <Checkbox disabled checked={true}/> : <Checkbox disabled checked={false}/>}</TableCell>
                                        <TableCell align="right">{account.isArchived? <Checkbox disabled checked={true} /> : <Checkbox disabled checked={false}/>}</TableCell>
                                        <TableCell align="right" >
                                            <IconButton aria-label="dashboard" onClick={() => {setMoreButtonIsClicked(!moreButtonIsClicked); setIndex(account.clientId)}} >
                                                <MoreVertIcon/>
                                            </IconButton>
                                        </TableCell>
                                        {moreButtonIsClicked && index===account.clientId? <Settings account={account} /> : null}
                                    </TableRow>
                                )
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </div>
    )
}

export default DisplayAllAccounts