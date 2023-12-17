# thi-thpt

## 1. First run

> For windows system, don't have the Make package, please install Make first

1. Open cmd or powershell on window with administrator
2. Run this command

```bash
choco install chocolatey
```

```bash
choco install make
```

```
make bootstrap
```

## 2. Run container

```
make up
```

## 3. Run code

- Add env file to run config
  => Start app

## 4. Migration database

After run container (step 3)

- Open file `sql/add_table.sql`, copy the contents
- Open browser, access to http://localhost:8080
- Select database `ptit` => SQL, paste contents in here
- Click `Go` to run SQL.
