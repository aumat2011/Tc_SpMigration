IF NOT EXISTS(SELECT 1 FROM sys.databases WHERE name='[epostrx]')
     CREATE DATABASE   [epostrx]
Go
IF NOT EXISTS(SELECT 1 FROM sys.databases WHERE name='[epostrx_workflow]')
     CREATE DATABASE   [epostrx_workflow]
Go
