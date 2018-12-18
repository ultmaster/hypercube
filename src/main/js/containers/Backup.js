import Typography from "@material-ui/core/Typography";
import SimpleLineChart from "../components/SimpleLineChart";
import SimpleTable from "../components/SimpleTable";
import React from "react";
import {withStyles} from "@material-ui/core";

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
            <React.Fragment>
                <Typography variant="h4" gutterBottom component="h2">
                    Orders
                </Typography>
                <Typography component="div" className={classes.chartContainer}>
                    <SimpleLineChart/>
                </Typography>
                <Typography variant="h4" gutterBottom component="h2">
                    Products
                </Typography>
                <div className={classes.tableContainer}>
                    <SimpleTable/>
                </div>
            </React.Fragment>
        );
    }
}

export default withStyles(styles)(Dashboard);