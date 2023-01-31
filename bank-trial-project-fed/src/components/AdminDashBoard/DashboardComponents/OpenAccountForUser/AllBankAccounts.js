import React, {Fragment} from 'react'
import styles from "./AllBankAccounts.module.css"
import PersonAddIcon from '@mui/icons-material/PersonAdd';
function OpenAccountForUser() {
    return (
        <Fragment>
            <div className={styles["header-container"]}>
                <div className={styles["icon-container"]}><PersonAddIcon/></div>
                <h3 className={styles["open-account-text-header"]}>Open New Account</h3>
            </div>
        </Fragment>
    )
}

export default OpenAccountForUser