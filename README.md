## Panichkin_ED_Java_2022_Liga

#### NO ARGS:
- /api/v1/cl/view_all
- /api/v1/cl/save
- /api/v1/cl/free_memory
- /api/v1/cl/exit

#### WITH ARGS
- /api/v1/cl/view task {id}
- /api/v1/cl/view user {id}
- /api/v1/cl/edit task {id} {new status id}
- /api/v1/cl/edit user {id} {new userName}
- /api/v1/cl/add task {taskName} {taskInfo} {userId} {dateTo}
- /api/v1/cl/add user {name}
- /api/v1/cl/delete task {id}
- /api/v1/cl/delete user {id}

#### MAX BUSY USER:
 - /api/v1/busy
 
 params: **type**  0/1/2  (NEW/IN_WORK/DONE)
 params: **min_date**  22.11.2000  (dateFrom)
 params: **max_date**  22.11.2222  (dateTo)
