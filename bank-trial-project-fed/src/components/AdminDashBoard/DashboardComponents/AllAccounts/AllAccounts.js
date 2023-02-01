import React, {Fragment} from 'react'
import styles from "./AllAccounts.module.css"
import PeopleIcon from '@mui/icons-material/People';
function AllAccounts() {

    return (
        <Fragment>
            <div className={styles["header-container"]}>
                <div className={styles["icon-container"]}><PeopleIcon/></div>
                <h3 className={styles["all-accounts-text-header"]}>All Users</h3>
            </div>
        </Fragment>
    )
}

export default AllAccounts