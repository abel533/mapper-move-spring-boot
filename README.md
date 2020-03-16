# 通用 Mapper 进阶实例：为什么好久都没更新了？

博客地址：https://liuzh.blog.csdn.net/article/details/104776347

日志：

```
Application              : ----------当前顺序----------
UserMapper.selectAll     : ==>  Preparing: SELECT id,sequence,name,py FROM user ORDER BY sequence ASC 
UserMapper.selectAll     : ==> Parameters: 
UserMapper.selectAll     : <==    Columns: ID, SEQUENCE, NAME, PY
UserMapper.selectAll     : <==        Row: 1, 1, 毕淑儒, BSR
UserMapper.selectAll     : <==        Row: 2, 2, 蔡兴熙, CXX
UserMapper.selectAll     : <==        Row: 3, 3, 曾三杰, ZSJ
UserMapper.selectAll     : <==        Row: 4, 4, 常元琴, CYQ
UserMapper.selectAll     : <==        Row: 5, 5, 陈栋芬, CDF
UserMapper.selectAll     : <==        Row: 6, 6, 陈宁婷, CNZ
UserMapper.selectAll     : <==        Row: 7, 7, 陈瑞, CR
UserMapper.selectAll     : <==        Row: 8, 8, 陈武宵, CWX
UserMapper.selectAll     : <==        Row: 9, 9, 陈晓丽, CXL
UserMapper.selectAll     : <==        Row: 10, 10, 陈涛, CT
UserMapper.selectAll     : <==      Total: 10
Application              : 将 3 号挪到 7 号后面
UserMapper.getSeqs       : ==>  Preparing: select id, sequence as seq from user where id in ( 3,7) 
UserMapper.getSeqs       : ==> Parameters: 
UserMapper.getSeqs       : <==    Columns: ID, SEQ
UserMapper.getSeqs       : <==        Row: 3, 3
UserMapper.getSeqs       : <==        Row: 7, 7
UserMapper.getSeqs       : <==      Total: 2
UserMapper.move          : ==>  Preparing: update user set sequence = sequence - 1 where sequence > ? and sequence <= ? 
UserMapper.move          : ==> Parameters: 3(Long), 7(Long)
UserMapper.move          : <==    Updates: 4
UserMapper.updateSeqById : ==>  Preparing: update user set sequence = ? where id = ? 
UserMapper.updateSeqById : ==> Parameters: 7(Long), 3(Long)
UserMapper.updateSeqById : <==    Updates: 1
Application              : ----------移动后的顺序----------
UserMapper.selectAll     : ==>  Preparing: SELECT id,sequence,name,py FROM user ORDER BY sequence ASC 
UserMapper.selectAll     : ==> Parameters: 
UserMapper.selectAll     : <==    Columns: ID, SEQUENCE, NAME, PY
UserMapper.selectAll     : <==        Row: 1, 1, 毕淑儒, BSR
UserMapper.selectAll     : <==        Row: 2, 2, 蔡兴熙, CXX
UserMapper.selectAll     : <==        Row: 4, 3, 常元琴, CYQ
UserMapper.selectAll     : <==        Row: 5, 4, 陈栋芬, CDF
UserMapper.selectAll     : <==        Row: 6, 5, 陈宁婷, CNZ
UserMapper.selectAll     : <==        Row: 7, 6, 陈瑞, CR
UserMapper.selectAll     : <==        Row: 3, 7, 曾三杰, ZSJ
UserMapper.selectAll     : <==        Row: 8, 8, 陈武宵, CWX
UserMapper.selectAll     : <==        Row: 9, 9, 陈晓丽, CXL
UserMapper.selectAll     : <==        Row: 10, 10, 陈涛, CT
UserMapper.selectAll     : <==      Total: 10
Application              : 将 4 号挪到 8 号前面
UserMapper.getSeqs       : ==>  Preparing: select id, sequence as seq from user where id in ( 4,8) 
UserMapper.getSeqs       : ==> Parameters: 
UserMapper.getSeqs       : <==    Columns: ID, SEQ
UserMapper.getSeqs       : <==        Row: 4, 3
UserMapper.getSeqs       : <==        Row: 8, 8
UserMapper.getSeqs       : <==      Total: 2
UserMapper.move          : ==>  Preparing: update user set sequence = sequence - 1 where sequence > ? and sequence < ? 
UserMapper.move          : ==> Parameters: 3(Long), 8(Long)
UserMapper.move          : <==    Updates: 4
UserMapper.updateSeqById : ==>  Preparing: update user set sequence = ? where id = ? 
UserMapper.updateSeqById : ==> Parameters: 7(Long), 4(Long)
UserMapper.updateSeqById : <==    Updates: 1
Application              : ----------移动后的顺序----------
UserMapper.selectAll     : ==>  Preparing: SELECT id,sequence,name,py FROM user ORDER BY sequence ASC 
UserMapper.selectAll     : ==> Parameters: 
UserMapper.selectAll     : <==    Columns: ID, SEQUENCE, NAME, PY
UserMapper.selectAll     : <==        Row: 1, 1, 毕淑儒, BSR
UserMapper.selectAll     : <==        Row: 2, 2, 蔡兴熙, CXX
UserMapper.selectAll     : <==        Row: 5, 3, 陈栋芬, CDF
UserMapper.selectAll     : <==        Row: 6, 4, 陈宁婷, CNZ
UserMapper.selectAll     : <==        Row: 7, 5, 陈瑞, CR
UserMapper.selectAll     : <==        Row: 3, 6, 曾三杰, ZSJ
UserMapper.selectAll     : <==        Row: 4, 7, 常元琴, CYQ
UserMapper.selectAll     : <==        Row: 8, 8, 陈武宵, CWX
UserMapper.selectAll     : <==        Row: 9, 9, 陈晓丽, CXL
UserMapper.selectAll     : <==        Row: 10, 10, 陈涛, CT
UserMapper.selectAll     : <==      Total: 10
Application              : 将 9 号挪到 2 号前面
UserMapper.getSeqs       : ==>  Preparing: select id, sequence as seq from user where id in ( 9,2) 
UserMapper.getSeqs       : ==> Parameters: 
UserMapper.getSeqs       : <==    Columns: ID, SEQ
UserMapper.getSeqs       : <==        Row: 2, 2
UserMapper.getSeqs       : <==        Row: 9, 9
UserMapper.getSeqs       : <==      Total: 2
UserMapper.move          : ==>  Preparing: update user set sequence = sequence + 1 where sequence >= ? and sequence < ? 
UserMapper.move          : ==> Parameters: 2(Long), 9(Long)
UserMapper.move          : <==    Updates: 7
UserMapper.updateSeqById : ==>  Preparing: update user set sequence = ? where id = ? 
UserMapper.updateSeqById : ==> Parameters: 2(Long), 9(Long)
UserMapper.updateSeqById : <==    Updates: 1
Application              : ----------移动后的顺序----------
UserMapper.selectAll     : ==>  Preparing: SELECT id,sequence,name,py FROM user ORDER BY sequence ASC 
UserMapper.selectAll     : ==> Parameters: 
UserMapper.selectAll     : <==    Columns: ID, SEQUENCE, NAME, PY
UserMapper.selectAll     : <==        Row: 1, 1, 毕淑儒, BSR
UserMapper.selectAll     : <==        Row: 9, 2, 陈晓丽, CXL
UserMapper.selectAll     : <==        Row: 2, 3, 蔡兴熙, CXX
UserMapper.selectAll     : <==        Row: 5, 4, 陈栋芬, CDF
UserMapper.selectAll     : <==        Row: 6, 5, 陈宁婷, CNZ
UserMapper.selectAll     : <==        Row: 7, 6, 陈瑞, CR
UserMapper.selectAll     : <==        Row: 3, 7, 曾三杰, ZSJ
UserMapper.selectAll     : <==        Row: 4, 8, 常元琴, CYQ
UserMapper.selectAll     : <==        Row: 8, 9, 陈武宵, CWX
UserMapper.selectAll     : <==        Row: 10, 10, 陈涛, CT
UserMapper.selectAll     : <==      Total: 10
Application              : 将 10 号挪到 1 号后面
UserMapper.getSeqs       : ==>  Preparing: select id, sequence as seq from user where id in ( 10,1) 
UserMapper.getSeqs       : ==> Parameters: 
UserMapper.getSeqs       : <==    Columns: ID, SEQ
UserMapper.getSeqs       : <==        Row: 1, 1
UserMapper.getSeqs       : <==        Row: 10, 10
UserMapper.getSeqs       : <==      Total: 2
UserMapper.move          : ==>  Preparing: update user set sequence = sequence + 1 where sequence > ? and sequence < ? 
UserMapper.move          : ==> Parameters: 1(Long), 10(Long)
UserMapper.move          : <==    Updates: 8
UserMapper.updateSeqById : ==>  Preparing: update user set sequence = ? where id = ? 
UserMapper.updateSeqById : ==> Parameters: 2(Long), 10(Long)
UserMapper.updateSeqById : <==    Updates: 1
Application              : ----------移动后的顺序----------
UserMapper.selectAll     : ==>  Preparing: SELECT id,sequence,name,py FROM user ORDER BY sequence ASC 
UserMapper.selectAll     : ==> Parameters: 
UserMapper.selectAll     : <==    Columns: ID, SEQUENCE, NAME, PY
UserMapper.selectAll     : <==        Row: 1, 1, 毕淑儒, BSR
UserMapper.selectAll     : <==        Row: 10, 2, 陈涛, CT
UserMapper.selectAll     : <==        Row: 9, 3, 陈晓丽, CXL
UserMapper.selectAll     : <==        Row: 2, 4, 蔡兴熙, CXX
UserMapper.selectAll     : <==        Row: 5, 5, 陈栋芬, CDF
UserMapper.selectAll     : <==        Row: 6, 6, 陈宁婷, CNZ
UserMapper.selectAll     : <==        Row: 7, 7, 陈瑞, CR
UserMapper.selectAll     : <==        Row: 3, 8, 曾三杰, ZSJ
UserMapper.selectAll     : <==        Row: 4, 9, 常元琴, CYQ
UserMapper.selectAll     : <==        Row: 8, 10, 陈武宵, CWX
UserMapper.selectAll     : <==      Total: 10
```