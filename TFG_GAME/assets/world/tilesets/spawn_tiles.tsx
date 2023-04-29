<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.0" name="spawn_tiles" tilewidth="32" tileheight="32" tilecount="5" columns="5">
 <image source="spawn_tiles.png" width="160" height="32"/>
 <tile id="0">
  <properties>
   <property name="spawn" value="player"/>
  </properties>
 </tile>
 <tile id="1">
  <properties>
   <property name="enemy_type" value=""/>
   <property name="skin" type="int" value="0"/>
   <property name="spawn" value="enemy"/>
  </properties>
 </tile>
 <tile id="4">
  <properties>
   <property name="char_type" type="int" value="0"/>
   <property name="message" value=""/>
   <property name="spawn" value="character"/>
  </properties>
 </tile>
</tileset>
