##master中
mysql> show master status;
+---------------+----------+--------------+------------------+-------------------+
| File          | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+---------------+----------+--------------+------------------+-------------------+
| binlog.000002 |      710 |              |                  |                   |
+---------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)

##slave连接到master
change master to
master_host='192.168.1.150',
master_port=3339,
master_user='slave',
master_password='123456',
master_log_file='mysql-bin.000001',
master_log_pos=5956;