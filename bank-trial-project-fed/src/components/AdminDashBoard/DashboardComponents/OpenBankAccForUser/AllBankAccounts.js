import React, {Fragment} from 'react'
import styles from "./AllBankAccounts.module.css"
import AddCardIcon from '@mui/icons-material/AddCard';
function OpenBankAccForUser() {
    return (
        <Fragment>
            <div className={styles["header-container"]}>
                <div className={styles["icon-container"]}><AddCardIcon/></div>
                <h3 className={styles["open-bank-text-header"]}>Open New Bank</h3>
            </div>
        </Fragment>
    )
}

export default OpenBankAccForUser