# iesl-auth
IESL Authentication Server


DB setup
--------

After installing postgres, create the postgres user and db that the example app uses

```bash
sudo -i -u postgres

createuser --interactive -P
```
set username to 'userapp'

set password to 'userpass'

answer no to remaining questions

```bash
createdb authdb
```

