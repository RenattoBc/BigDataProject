# Big Data Project of Stack OverFlow Posts

Main goal of this project is to obtain insights and key information about Posts most of the developers made through the platform Stack Overflow.
In order to obtain that insights the data will load, transform and analyzed through in and structured process.

## 1. Data Set Description
### 1.1 Source and Description

The project will use the Database of Stack OverFlow Posts. The database can be consult trough next link https://www.brentozar.com/archive/2015/10/how-to-download-the-stack-overflow-database-via-bittorrent/

#### Posts Structure
|        Column         | Description                                                                                                                                |
|:---------------------:|--------------------------------------------------------------------------------------------------------------------------------------------|
|          Id           | Id of the Post                                                                                                                             |
|      PostTypeId       | Id of the Post Type, is listed in the PostTypes table                                                                                      |
|   AcceptedAnswerId    | Only present if PostTypeId = 1                                                                                                             |
|       ParentId        | Only present if PostTypeId = 2                                                                                                             |
|     CreationDate      | Date time Post was created on                                                                                                              |
|         Score         | Score of the post.                                                                                                                         |
|       ViewCount       | Number of times the post has been seen.                                                                                                    |
|         Body          | HTML of the Post                                                                                                                           |
|      OwnerUserId      | Id of the owner of the post (only present if user has not been deleted; always -1 for tag wiki entries, i.e. the community user owns them) |
|         Body          | HTML of the Post                                                                                                                           |
|   OwnerDisplayName    | Name of the owner of the Post                                                                                                              |
|   LastEditorUserId    | If of the last editor of the Post                                                                                                          |
| LastEditorDisplayName | Name of the last editor of the Post                                                                                                        |
|     LastEditDate      | Date Time of the last edition of the Post                                                                                                  |
|   LastActivityDate    | Date Time of the last activity made in the Post                                                                                            |
|         Title         | Title of the Post                                                                                                                          ||       Body       | HTML of the Post                                                                                                                           ||       Body       | HTML of the Post                                                                                                                           ||       Body       | HTML of the Post                                                                                                                           ||       Body       | HTML of the Post                                                                                                                           ||       Body       | HTML of the Post                                                                                                                           ||       Body       | HTML of the Post                                                                                                                           ||       Body       | HTML of the Post                                                                                                                           ||       Body       | HTML of the Post                                                                                                                           |
|         Tags          | Tags the Posts has                                                                                                                         |
|      AnswerCount      | Number of Answers of the Post                                                                                                              |
|     CommentCount      | Number of Comments of the Post                                                                                                             |
|     FavoriteCount     | Number of times users put the Post with favorite mark                                                                                      |
|      ClosedDate       | Close Date of the Post                                                                                                                     ||       Body       | HTML of the Post                                                                                                                           |
|  CommunityOwnedDate   | Present only if post is community wiki'd                                                                                                   |

### 1.2 EDA REPORT
The exploratory analysis was made with a function that summarize numbers of nulls and percentage of nulls by column in a given Dataframe.
It also shows the total percentage of cells in null.
The function works as follows:
1. It receives a Dataframe
2. Obtain data to make some calculations
3. Iterate through column name in order to calculate null values in the rows of the column
4. Append it into a StringBuilder that is going to be shown
5. Return the String concatenate it with the values
```Scala
    def getPostsEda(dfPosts: DataFrame): String = {
      val returnString = new mutable.StringBuilder("\nEDA report for Posts Dataframe\n")
      val dfPostsTotal = dfPosts.count()
      val columnNames = dfPosts.columns
      val columnNumber = columnNames.length
      returnString.append(s"Numbers of rows -> $dfPostsTotal\n")
      returnString.append(s"Numbers of columns -> $columnNumber\n")
      var numberOfCellsInNull : Long = 0
      returnString.append("Column Report:\n")
      columnNames.foreach {columnName =>
        var columnNull : Long = 0
        if (dfPosts.schema(columnName).dataType.typeName == "timestamp") {
          columnNull = dfPosts.filter(dfPosts(columnName).isNull || dfPosts(columnName) === "").count()
        } else {
          columnNull = dfPosts.filter(dfPosts(columnName).isNull || dfPosts(columnName) === "" || dfPosts(columnName).isNaN).count()
        }
        numberOfCellsInNull += columnNull
        returnString.append(s"Number of $columnName in null -> $columnNull \n")
        returnString.append(s"Percentage of $columnName in null -> ${round(columnNull * 100 / dfPostsTotal.toFloat,3)} % \n")
      }
      returnString.append(s"\nNumber of cells in null $numberOfCellsInNull")
      returnString.append(s"\nPercentage of cells in null ${numberOfCellsInNull * 100 / (columnNumber * dfPostsTotal).toFloat}%\n")
      returnString.toString()
    }
```

Results:
1. The number of rows analyzed were 17,142,169  rows and 20 columns.
2. The number of missing cells where 24.415%, due to the structure of the table that contain Posts and Answers this behaviour will not impact in the results obtain.

```
EDA report for Posts Dataframe
Numbers of rows -> 17142169
Numbers of columns -> 20
Column Report:
Number of Id in null -> 0 
Percentage of Id in null -> 0.0 % 
Number of AcceptedAnswerId in null -> 0 
Percentage of AcceptedAnswerId in null -> 0.0 % 
Number of AnswerCount in null -> 0 
Percentage of AnswerCount in null -> 0.0 % 
Number of Body in null -> 6948 
Percentage of Body in null -> 0.041 % 
Number of ClosedDate in null -> 16872749 
Percentage of ClosedDate in null -> 98.428 % 
Number of CommentCount in null -> 0 
Percentage of CommentCount in null -> 0.0 % 
Number of CommunityOwnedDate in null -> 17050460 
Percentage of CommunityOwnedDate in null -> 99.465 % 
Number of CreationDate in null -> 0 
Percentage of CreationDate in null -> 0.0 % 
Number of FavoriteCount in null -> 0 
Percentage of FavoriteCount in null -> 0.0 % 
Number of LastActivityDate in null -> 0 
Percentage of LastActivityDate in null -> 0.0 % 
Number of LastEditDate in null -> 10542206 
Percentage of LastEditDate in null -> 61.499 % 
Number of LastEditorDisplayName in null -> 16949596 
Percentage of LastEditorDisplayName in null -> 98.877 % 
Number of LastEditorUserId in null -> 0 
Percentage of LastEditorUserId in null -> 0.0 % 
Number of OwnerUserId in null -> 0 
Percentage of OwnerUserId in null -> 0.0 % 
Number of ParentId in null -> 0 
Percentage of ParentId in null -> 0.0 % 
Number of PostTypeId in null -> 0 
Percentage of PostTypeId in null -> 0.0 % 
Number of Score in null -> 0 
Percentage of Score in null -> 0.0 % 
Number of Tags in null -> 11141945 
Percentage of Tags in null -> 64.997 % 
Number of Title in null -> 11141768 
Percentage of Title in null -> 64.996 % 
Number of ViewCount in null -> 0 
Percentage of ViewCount in null -> 0.0 % 

Number of cells in null 83705672
Percentage of cells in null 24.415134%
```

### 1.3 Data Cleansing

The process of data cleansing was done by:
1. Filling rows in column "LastEditorDisplayName", "Tags" and "Title" with the value "unknown".
2. The TimeStamp column were leaved in null because it doesn't affect the processing of the values.

The eda report shows the following result after the data cleansing process:

```
EDA report for Posts Dataframe
Numbers of rows -> 17142169
Numbers of columns -> 20
Column Report:
Number of Id in null -> 0 
Percentage of Id in null -> 0.0 % 
Number of AcceptedAnswerId in null -> 0 
Percentage of AcceptedAnswerId in null -> 0.0 % 
Number of AnswerCount in null -> 0 
Percentage of AnswerCount in null -> 0.0 % 
Number of Body in null -> 6948 
Percentage of Body in null -> 0.041 % 
Number of ClosedDate in null -> 16872749 
Percentage of ClosedDate in null -> 98.428 % 
Number of CommentCount in null -> 0 
Percentage of CommentCount in null -> 0.0 % 
Number of CommunityOwnedDate in null -> 17050460 
Percentage of CommunityOwnedDate in null -> 99.465 % 
Number of CreationDate in null -> 0 
Percentage of CreationDate in null -> 0.0 % 
Number of FavoriteCount in null -> 0 
Percentage of FavoriteCount in null -> 0.0 % 
Number of LastActivityDate in null -> 0 
Percentage of LastActivityDate in null -> 0.0 % 
Number of LastEditDate in null -> 10542206 
Percentage of LastEditDate in null -> 61.499 % 
Number of LastEditorDisplayName in null -> 2 
Percentage of LastEditorDisplayName in null -> 0.0 % 
Number of LastEditorUserId in null -> 0 
Percentage of LastEditorUserId in null -> 0.0 % 
Number of OwnerUserId in null -> 0 
Percentage of OwnerUserId in null -> 0.0 % 
Number of ParentId in null -> 0 
Percentage of ParentId in null -> 0.0 % 
Number of PostTypeId in null -> 0 
Percentage of PostTypeId in null -> 0.0 % 
Number of Score in null -> 0 
Percentage of Score in null -> 0.0 % 
Number of Tags in null -> 0 
Percentage of Tags in null -> 0.0 % 
Number of Title in null -> 1 
Percentage of Title in null -> 0.0 % 
Number of ViewCount in null -> 0 
Percentage of ViewCount in null -> 0.0 % 

Number of cells in null 44472366
Percentage of cells in null 12.971627%
```

## 2. How to execute the solution

### 2.1 Database configuration
The datasource of the information was download in the next formats:
- .mdf (Master Database File used by Microsoft SQL Server to store user data)
- .ndf (Secondary database file used by Microsoft SQL Server to store user data)
- .ldf (Transaction log that allows you to perform “point in time” restores if you have system failures)
 
In order to load the information to be read, it should be loaded to a SQL Server instance. To achieve this we'll create an instance of SQL Server in docker and attach the files with SSMS (SQL Server Management Studio):
1. Run the following command in a terminal ```docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=sql@server2022" --name sqlserver -p 1456:1433 -d mcr.microsoft.com/mssql/server:2019-latest```, this will generate a container for an instance of SQL Server with the following configurations:
   - Username: "sa"
   - Password: "sql@server2022"
   - Port: "1456"
   - Version: Latest of 2019 - (15.0.4261.1)
2. Copy the files into the container with the following commands:
```
    docker cp StackOverflow2013_1.mdf sqlserver:/var/opt/mssql/data/StackOverflow2013_1.mdf
    docker cp StackOverflow2013_2.ndf sqlserver:/var/opt/mssql/data/StackOverflow2013_2.ndf
    docker cp StackOverflow2013_3.ndf sqlserver:/var/opt/mssql/data/StackOverflow2013_3.ndf
    docker cp StackOverflow2013_4.ndf sqlserver:/var/opt/mssql/data/StackOverflow2013_4.ndf
    docker cp StackOverflow2013_log.ldf sqlserver:/var/opt/mssql/data/StackOverflow2013_log.ldf
```
3. Enter inside the container in order to change permission of the files with root privileges 
```
    docker exec -it --user root sqlserver bash
    chmod 777 /var/opt/mssql/data/StackOverflow2013_1.mdf
    chmod 777 /var/opt/mssql/data/StackOverflow2013_2.ndf
    chmod 777 /var/opt/mssql/data/StackOverflow2013_3.ndf
    chmod 777 /var/opt/mssql/data/StackOverflow2013_4.ndf
    chmod 777 /var/opt/mssql/data/StackOverflow2013_log.ldf
```
4. Attach the .mdf to the SQL Server Instance.

### 3. Dataset Insights and their reports
#### 3.1. Who are the top 20 users with the highest average answer score excluding community wiki / closed posts?

``` Scala
+-------+------------------+-------+--------------------+
|User Id|          Username|Answers|Average Answer Score|
+-------+------------------+-------+--------------------+
| 613430|        Tom Wadley|     26|  351.15384615384613|
| 214090|           hallski|     28|  290.17857142857144|
|  95592|    Matthew Rankin|     85|   220.3294117647059|
|  25449|  Tsvetomir Tsonev|     48|            178.0625|
|  39933|          genehack|     52|  166.09615384615384|
|    264|         Xenph Yan|     22|   160.9090909090909|
|    462|         Greg Case|     24|  144.95833333333334|
| 192810|      Misko Hevery|     28|              143.75|
|  19082|     Mike Hordecki|     34|  141.35294117647058|
|  22850|               Tom|     31|  140.29032258064515|
|   8985|      Ryan McGeary|    127|  139.85826771653544|
|  98068|       Eric Nguyen|     27|  122.96296296296296|
|   1057|   Harley Holcombe|     84|  120.22619047619048|
|  15872|       Adam Franco|     32|            117.1875|
| 259038| Josh David Miller|    113|    113.070796460177|
|  20846|          flatline|     32|           105.15625|
|    822|        Reto Meier|     66|   104.8030303030303|
|  25472|             VolkA|     21|  102.66666666666667|
| 207162|            Trevor|     26|   98.65384615384616|
|  41165|Patrick Harrington|     26|   98.53846153846153|
+-------+------------------+-------+--------------------+
```

**Code**

```Scala
    val postsJoinUsers = postsDfClean.col("OwnerUserId") === usersDf.col("Id")
    val postsAndUsers = postsDfClean.join(usersDf, postsJoinUsers, "inner")
    postsAndUsers
    .filter("PostTypeId = 2 and CommunityOwnedDate is null and ClosedDate is null")
    .groupBy(usersDf.col("Id"), usersDf.col("DisplayName"))
    .agg(
      countDistinct(postsDf.col("Id")).as("Answers"),
      avg(col("Score")).as("Average Answer Score")
    )
    .where("Answers > 20")
    .orderBy(desc("Average Answer Score"))
    .select(
      col("Id").as("User Id"),
      col("DisplayName").as("Username"),
      col("Answers"),
      col("Average Answer Score")
    )
    .limit(20)
    .show(20)
```


#### 3.2. Who are the users with highest accept rate of their answers?
``` Scala
+-------+------------------+-----------------+---------------+-----------------+
|     Id|       DisplayName|Number of Answers|Number Accepted| Accepted Percent|
+-------+------------------+-----------------+---------------+-----------------+
| 342740|              Prix|              258|            258|            100.0|
| 864604|            joshnh|               61|             61|            100.0|
|2887841|         tasseKATT|               30|             30|            100.0|
| 664470|     Jérôme Mahuet|               12|             12|            100.0|
| 961092| Dmitry Verhoturov|               12|             12|            100.0|
|1615819|     Mr. Bojangles|               12|             12|            100.0|
|1198890|           anfilat|               11|             11|            100.0|
|2970237|     thenewseattle|               23|             22|95.65217391304348|
|  54937|   Sedat Kapanoglu|              109|            104|95.41284403669725|
|1072272|               Tom|               38|             36|94.73684210526316|
| 941072|             lolol|               34|             32|94.11764705882354|
| 479947|              arby|               17|             16|94.11764705882354|
| 196105|               ski|               16|             15|            93.75|
|1406062|       user1406062|               14|             13|92.85714285714286|
|2161467|Andreas Lochbihler|               14|             13|92.85714285714286|
| 502899|            JayneT|               14|             13|92.85714285714286|
|2009882|         york.beta|               13|             12| 92.3076923076923|
| 825469|    Heather Miller|               13|             12| 92.3076923076923|
| 249845|           Dribbel|               13|             12| 92.3076923076923|
| 784771|       Swordslayer|               13|             12| 92.3076923076923|
+-------+------------------+-----------------+---------------+-----------------+
```

**Code**
```Scala
    val postsParentDf = spark.read.parquet("src/main/resources/posts.parquet")
    val postsUsersJoinCondition = col("Posts.OwnerUserId") === col("Users.Id")
    val acceptanceDf = postsDfClean.as("Posts")
      .join(usersDf.as("Users"), postsUsersJoinCondition, "inner")
      .join(postsParentDf.as("PostParent"), col("PostParent.Id") === col("Posts.ParentId"), "inner")
      .filter("(PostParent.OwnerUserId != Users.Id or PostParent.OwnerUserId is null)")
      .withColumn("AcceptedAnswerFlag", expr("case when PostParent.AcceptedAnswerId = Posts.Id then 1 else 0 end"))
    
    acceptanceDf
      .groupBy("Users.Id", "Users.DisplayName")
      .agg(
        count("Users.Id").as("Number of Answers"),
        sum("AcceptedAnswerFlag").as("Number Accepted"),
        (sum("AcceptedAnswerFlag") * 100 / count("Posts.Id")).as("Accepted Percent")
      )
      .orderBy(desc("Accepted Percent"), desc("Number of Answers"))
      .filter(col("Number of Answers") > 10)
      .limit(50)
      .show()
```

#### 3.3. Top Users by Number of Bounties Won?
``` Scala
+-------+----------------+------------+
|User Id|        Username|Bounties Won|
+-------+----------------+------------+
|  17034|    Hans Passant|         216|
| 403671|   Simon Mourier|         132|
| 157882|          BalusC|         114|
| 315935|            Oleg|          86|
|   6309|            VonC|          86|
|  29407|  Darin Dimitrov|          71|
| 847363|           Yahia|          62|
| 517815|         MrGomez|          62|
|2334192|James Holderness|          60|
| 149573|       Todd Main|          59|
| 493939|        Luksprog|          59|
|  22656|       Jon Skeet|          55|
|  23354|    Marc Gravell|          53|
| 833622| Sherif elKhatib|          52|
|1528401|       Glen Best|          51|
|2558882|          Vikram|          50|
| 367456|           hakre|          48|
|1226894|            Baba|          48|
| 857361|          Ronnie|          47|
| 326480|            mdma|          47|
+-------+----------------+------------+
```

**Code**
```Scala
    val votesDf = spark.read.parquet("src/main/resources/Votes.parquet")
    val votesPostsJoinCondition = col("Votes.PostId") === col("Posts.Id")
    val votesUsersPostsJoinDf = votesDf.as("Votes")
      .join(postsDfClean.as("Posts"), votesPostsJoinCondition, "inner")
      .join(usersDf.as("Users"), postsUsersJoinCondition, "inner")
    
    votesUsersPostsJoinDf
      .filter("Votes.VoteTypeId = 9")
      .groupBy("Posts.OwnerUserId", "Users.DisplayName")
      .agg(
        count("Votes.Id").as("Bounties Won")
      )
      .withColumnRenamed("DisplayName", "Username")
      .withColumnRenamed("OwnerUserId", "User Id")
      .orderBy(desc("Bounties Won"))
      .show()
```

#### 3.4. What answers are the most upvoted of All Time?
```Scala
+--------+--------------------+----------+
| Post Id|            Question|Vote Count|
+--------+--------------------+----------+
|11227902|<p><strong>You ar...|      9850|
  |  179147|<h1>Amending the ...|      4581|
  | 6841479|<p>It's a time zo...|      4482|
  | 1732454|<p>You can't pars...|      4449|
  |  477819|<p>For JSON text:...|      4275|
  |  506004|<h2>One does not ...|      4096|
  | 6866485|<p>Undoing a comm...|      3939|
  |  231855|<p>To understand ...|      3741|
  |15012542|<h1>1. Don't desi...|      3370|
  |  927386|<h3>Undo a commit...|      3347|
  |  178450|<p>Since the ques...|      3247|
  |  487278|<p>Quick note, th...|      3207|
  | 6445794|<blockquote><p...|      3047|
  | 2003515|<h1>Executive Sum...|      2960|
  | 1220118|<h3>Your problem ...|      2867|
  | 1789952|<p>Here is a list...|      2558|
  | 1642035|<blockquote>  <p...|      2534|
  | 6581949|<h1>Classes as ob...|      2523|
  |  901144|<p>You don't need...|      2506|
  |   60496|<p><strong>Use pr...|      2408|
  +--------+--------------------+----------+
```

**Code**

```Scala
    val votesPostsJoinDf = votesDf.as("Votes")
        .join(postsDfClean.as("Posts"), votesPostsJoinCondition, "inner")
    
      votesPostsJoinDf
        .filter("Posts.PostTypeId = 2 and Votes.VoteTypeId = 2")
        .groupBy("Votes.PostId", "Posts.Body")
        .agg(
          count("Votes.PostId").as("Vote Count")
        )
        .withColumnRenamed("Body", "Question")
        .withColumnRenamed("PostId", "Post Id")
        .orderBy(desc("Vote Count"))
        .show()
```

#### 3.5. What are the distribution of Users Activity Per Hour?

```Scala
+----+----------------+
|Hour|Activity Traffic|
+----+----------------+
|   0|         1100247|
|   1|          999439|
|   2|          954445|
|   3|          954951|
|   4|         1013457|
|   5|         1144330|
|   6|         1356188|
|   7|         1535723|
|   8|         1685580|
|   9|         1935425|
|  10|         1960358|
|  11|         1979714|
|  12|         2126399|
|  13|         2331304|
|  14|         2478386|
|  15|         2493236|
|  16|         2326985|
|  17|         2171764|
|  18|         2133639|
|  19|         2112122|
|  20|         2075659|
|  21|         1904825|
|  22|         1596783|
|  23|         1305940|
+----+----------------+
```

**Code**
```Scala
  val commentsDf = spark.read.parquet("src/main/resources/comments.parquet")
  val hoursFromPostsDf = postsDfClean.select(hour(col("CreationDate")).as("Hour"), lit(1).as("Counter"))
  val hoursFromCommentsDf = commentsDf.select(hour(col("CreationDate")).as("Hour"), lit(1).as("Counter"))
  val distinctHoursFromPostDf = postsDfClean.select(hour(col("CreationDate")).as("Hour")).dropDuplicates()
  hoursFromPostsDf.union(hoursFromCommentsDf).as("HoursUnion")
    .join(distinctHoursFromPostDf.as("DistinctHours"), col("HoursUnion.Hour") === col("DistinctHours.Hour"), "right")
    .groupBy("DistinctHours.Hour")
    .agg(
      count("HoursUnion.Counter").as("Activity Traffic")
    )
    .orderBy(asc("DistinctHours.Hour"))
    .show(24)
```

#### 3.6. Questions Count by Month
```Scala
+-------+--------+
|   Date|Quantity|
+-------+--------+
|2013-12|  176978|
|2013-11|  187169|
|2013-10|  195956|
|2013-09|  176481|
|2013-08|  182375|
|2013-07|  189663|
|2013-06|  170236|
|2013-05|  179042|
|2013-04|  183970|
|2013-03|  186915|
|2013-02|  165253|
|2013-01|  170346|
|2012-12|  145377|
|2012-11|  158820|
|2012-10|  162698|
|2012-09|  142393|
|2012-08|  151809|
|2012-07|  153002|
|2012-06|  140443|
|2012-05|  144050|
```

**Code**
```Scala
    val postsLinksDf = spark.read.parquet("src/main/resources/postLinks.parquet")
      postsDfClean.as("Posts")
        .join(postsLinksDf.as("PostsLinks"), col("Posts.Id") === col("PostsLinks.PostId"), "left")
        .join(postsParentDf.as("PostParent"), col("PostParent.Id") === col("PostsLinks.RelatedPostId"), "left")
        .where("Posts.PostTypeId = 1")
        .select(
          date_format(col("Posts.CreationDate"), "yyyy-MM").as("Date"),
          col("Posts.Id")
        )
        .groupBy("Date")
        .agg(
          count("Posts.Id").as("Quantity")
        )
        .orderBy(desc("Date"))
        .show()
```