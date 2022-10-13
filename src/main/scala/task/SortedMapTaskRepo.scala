package task

import scala.collection.mutable.SortedMap

class SortedMapTaskRepo {
  var todoList: SortedMap[Int, Todo] = SortedMap.empty[Int, Todo]
  var doingList: SortedMap[Int, Doing] = SortedMap.empty[Int, Doing]

  def getPong(): String = "pong"

  def createNewTodo(todo: Todo): Int = {
    var item = todoList.lastOption
    var id = item match {
      case Some(value) => value._1 + 1
      case None        => 0
    }

    todoList.addOne(id -> Todo(id, todo.detail))
    id
  }

  def moveToDoing(todo: Todo): Option[Doing] = {
    var item = todoList.get(todo.id)

    item match {
      case Some(value) =>
        todoList.remove(value.id)
        doingList.addOne(value.id -> Doing(value.id, value.detail))
        Some(doingList.last._2)

      case None => None
    }
  }

  def getAllItemsInTodo(): List[Todo] = todoList.values.toList
  def getAllItemsInDoing(): List[Doing] = doingList.values.toList
}
