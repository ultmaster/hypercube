import Typography from "@material-ui/core/Typography";
import SimpleLineChart from "../components/SimpleLineChart";
import SimpleTable from "../components/SimpleTable";
import React from "react";
import {withStyles} from "@material-ui/core";
import DashboardForm from "./DashboardForm";

const styles = theme => ({
    chartContainer: {
        marginLeft: -22,
    },
    tableContainer: {
        height: 320,
    }
});

class Dashboard extends React.Component {

    render() {
        const { classes } = this.props;

        return (
            <DashboardForm />
        );
    }
}

export default withStyles(styles)(Dashboard);