package HelpersClasses

object HelperSchema {

  case class PostData(
                       acceptedAnswerId: Option[Long],
                       answerCount: Option[Long],
                       body: Option[String],
                       closedDate: Option[String],
                       commentCount: Option[Long],
                       communityOwnedDate: Option[String],
                       creationDate: Option[String],
                       favoriteCount: Option[Long],
                       id: Option[Long],
                       lastActivityDate: Option[String],
                       lastEditDate: Option[String],
                       lastEditorUserId: Option[Long],
                       ownerDisplayName: Option[String],
                       ownerUserId: Option[Long],
                       parentId: Option[Long],
                       postTypeId: Option[Long],
                       score: Option[Long],
                       tags: Option[String],
                       title: Option[String],
                       value: Option[String],
                       viewCount: Option[Long]
                     )

  case class PostLinksModelData(
                                 id: Long,
                                 linkTypeId: Long,
                                 postId: Long,
                                 relatedPostId: Long,
                                 acceptedAnswerId: Long,
                                 answerCount: Long,
                                 body: String,
                                 closedDate: String,
                                 commentCount: Long,
                                 communityOwnedDate: String,
                                 creationDate: String,
                                 favoriteCount: Long,
                                 lastActivityDate: String,
                                 lastEditDate: String,
                                 lastEditorDisplayName: String,
                                 lastEditorUserId: Long,
                                 ownerDisplayName: String,
                                 ownerUserId: Long,
                                 parentId: Long,
                                 postTypeId: Long,
                                 score: Long,
                                 tags: String,
                                 title: String,
                                 viewCount: Long
                               )

  case class PostCommentsModelData(
                                    id: Long,
                                    userId: Long,
                                    userDisplayName: String,
                                    postId: Long,
                                    text: String,
                                    commentScore: Long,
                                    acceptedAnswerId: Long,
                                    answerCount: Long,
                                    body: String,
                                    closedDate: String,
                                    commentCount: Long,
                                    communityOwnedDate: String,
                                    creationDate: String,
                                    favoriteCount: Long,
                                    lastActivityDate: String,
                                    lastEditDate: String,
                                    lastEditorDisplayName: String,
                                    lastEditorUserId: Long,
                                    ownerDisplayName: String,
                                    ownerUserId: Long,
                                    parentId: Long,
                                    postTypeId: Long,
                                    score: Long,
                                    tags: String,
                                    title: String,
                                    value: String,
                                    viewCount: Long
                                  )

  case class PostVotesModelData(
                                 id: Long,
                                 bountyAmount: Option[Long],
                                 postId: Long,
                                 userId: Option[Long],
                                 voteTypeId: Long,
                                 acceptedAnswerId: Long,
                                 answerCount: Long,
                                 body: String,
                                 closedDate: String,
                                 commentCount: Long,
                                 communityOwnedDate: String,
                                 creationDate: String,
                                 favoriteCount: Long,
                                 lastActivityDate: String,
                                 lastEditDate: String,
                                 lastEditorDisplayName: String,
                                 lastEditorUserId: Long,
                                 ownerDisplayName: String,
                                 ownerUserId: Long,
                                 parentId: Long,
                                 postTypeId: Long,
                                 score: Long,
                                 tags: String,
                                 title: String,
                                 viewCount: Long
                               )
}
