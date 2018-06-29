package com.test.NpcBuilder

import scala.util.{Random}

class NPC(val npcName: String = "", val npcClass: String = "", val npcTitle: String = "") {
  def nameChoices: Array[String] = Array("Mal", 
    "Argyn",
    "Ulrich",
    "Viktor",
    "Dendrath",
    "Clarence",
    "Molloc",
    "Gunmaar",
    "Reindrath",
    "Ogden",
    "Tess",
    "Visca")
  
  def titleChoices: Array[String] = Array("the Enormous", 
    "Bone-breaker", 
    "the Chivalrous", 
    "the Destroyer", 
    "the Defiler",
    "the White Rose",
    "the Silver Storm",
    "Death's Shadow",
    "Oathbreaker")

  def classChoices: Array[String] = Array("Barbarian",
    "Bard",
    "Cleric",
    "Druid",
    "Fighter",
    "Monk",
    "Paladin",
    "Ranger",
    "Rogue",
    "Sorcerer",
    "Warlock",
    "Wizard")
  def r = Random

  //def name = nameChoices(r.nextInt(nameChoices.length : Int))
  var name = ""
  if (npcName != null && npcName != "") {
    name = npcName
  }
  else {
    name = nameChoices(r.nextInt(nameChoices.length : Int))
  }
  //def name = nameChoices(index)

  var title = ""
  if (npcTitle != null && npcTitle != "") {
    title = npcTitle
  }
  else {
    title = titleChoices(r.nextInt(titleChoices.length : Int))
  }
  
  var characterClass = ""
  if (npcClass != null && npcClass != "") {
    characterClass = npcClass
  }
  else {
    characterClass = classChoices(r.nextInt(classChoices.length : Int))
  }
  
  def level = r.nextInt(20) + 1
}

object NpcBuilder {
  def main(args: Array[String]) {
    println("What name should your npc have (leave blank for random)?")
    val npcName = scala.io.StdIn.readLine()

    println("What title should your npc have (leave blank for random)?")
    val npcTitle = scala.io.StdIn.readLine()

    println("What class should your npc have (leave blank for random)?")
    val npcClass = scala.io.StdIn.readLine()

    println("Generating an npc on the fly!")

    val npc = new NPC(npcName = npcName, npcClass = npcClass, npcTitle = npcTitle)
    println(npc.name + ", " + npc.title + ", a level " + npc.level + " " + npc.characterClass)
  }
}


