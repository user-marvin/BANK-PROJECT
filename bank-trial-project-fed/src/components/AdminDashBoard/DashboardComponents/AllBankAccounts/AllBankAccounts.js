import React, {Fragment} from 'react'
import styles from "./AllBankAccounts.module.css"
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';
function AllBankAccounts() {
    return (
        <Fragment>
            <div className={styles["header-container"]}>
                <div className={styles["icon-container"]}><AccountBalanceIcon/></div>
                <h3 className={styles["all-bank-accounts-text-header"]}>All Bank Accounts</h3>
            </div>
        </Fragment>
    )
}

export default AllBankAccounts