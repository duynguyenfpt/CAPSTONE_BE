package com.web_service.entity.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Bosses {
	@Id
	 public ObjectId id;
	 public String name;
	 public String weapon;
	 public String weakness;

	 // Constructors
	 public Bosses() {}
	 public Bosses(ObjectId id, String name, String weapon, String weakness) {
	  this.id = id;
	  this.name = name;
	  this.weapon = weapon;
	  this.weakness = weakness;
	 }

	 // ObjectId needs to be converted to string
	 public String get_id() {
	  return id.toHexString();
	 }
	 public void set_id(ObjectId _id) {
	  this.id = _id;
	 }

	 public String getName() {
	  return name;
	 }
	 public void setName(String name) {
	  this.name = name;
	 }

	 public String getWeapon() {
	  return weapon;
	 }
	 public void setWeapon(String weapon) {
	  this.weapon = weapon;
	 }

	 public String getWeakness() {
	  return weakness;
	 }
	 public void setWeakness(String weakness) {
	  this.weakness = weakness;
	 }
}
