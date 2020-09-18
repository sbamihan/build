RMDIR "C:\tmp" /S /Q
mkdir "C:\tmp"
xcopy C:\kafka-backup-folders C:\tmp /O /X /E /H /K
RMDIR "C:\app\kafka_2.12-2.3.0\logs" /S /Q