import { AppBar, Typography, Toolbar, Grid } from "@material-ui/core";

const NavBar = ({ user }) => {
  const NavBarForPharmacist = (
    <Toolbar>
      <Grid container spacing={1}>
        <Grid item xs={6}>
          <Typography>Hay {user}</Typography>
        </Grid>
        <Grid item xs={6} container spacing={1} style={{ textAlign: "right" }}>
          <Grid item xs={3}></Grid>
          <Grid item xs={3}></Grid>
          <Grid item xs={3}>
            <Typography>
              <a
                href="/pharmacist/appointment"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Profile
              </a>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <a href="/" style={{ color: "#fafafa", textDecoration: "none" }}>
                Sing out
              </a>
            </Typography>
          </Grid>
        </Grid>
      </Grid>
    </Toolbar>
  );

  return (
    <>
      <AppBar position="static">
        {user === "pharmacist" && NavBarForPharmacist}
      </AppBar>
    </>
  );
};

export default NavBar;
