# serial-number-generator
## 项目基于springboot开发，实现唯一ID的生成
导入IDEA开发工具，build项目即可
### 自定义ID生成
custom包下的IdWorkerCustom类，通过AtomicInteger和时间戳实现唯一ID生成，如果上一个timestamp与新产生的相等，则sequence加一，如果不相等，下次再使用时sequence是新值，从头开始计数。
可自定义最后几位， 定义四位 时间戳+0001开始，定义五位 时间戳+00001开始，以此类推，且保持固定长度。因为是基于内存，不走数据库，没有磁盘IO，所以效率高
测试单元中custom包下模拟100个线程同时执行，测试效果ok。因为使用了同步锁，所以线程安全
### 基于雪花算法实现
snowflake包下的Snow类，基于推特开源的SnowFlake实现，测试方法同上


### 第三种方式可以使用美团开源的Leaf框架实现，其中有Leaf-segment和Leaf-snowflake两种方式，github下载源码部署 
