sleep 90s

/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'SecretP@ssword!!' -i create_db.sql
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'SecretP@ssword!!' -d epostrx -i create_tables_epostrx.sql
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'SecretP@ssword!!' -d epostrx_workflow -i create_tables_epostrx_workflow.sql
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'SecretP@ssword!!' -d epostrx -i insert_scripts_epostrx.sql
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'SecretP@ssword!!' -d epostrx_workflow -i insert_scripts_epostrx_workflow.sql
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'SecretP@ssword!!' -d epostrx -i create_functions_epostrx.sql
