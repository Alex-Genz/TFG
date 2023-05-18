<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.1" name="spawn_tiles" tilewidth="32" tileheight="32" tilecount="6" columns="6">
 <image source="spawn_tiles.png" width="192" height="32"/>
 <tile id="0">
  <properties>
   <property name="spawn" value="player"/>
  </properties>
 </tile>
 <tile id="1">
  <properties>
   <property name="enemy_type" value=""/>
   <property name="path_size_x" type="float" value="0"/>
   <property name="path_size_y" type="float" value="0"/>
   <property name="persecution_speed" type="float" value="0"/>
   <property name="skin" type="int" value="0"/>
   <property name="spawn" value="enemy"/>
  </properties>
 </tile>
 <tile id="4">
  <properties>
   <property name="char_type" type="int" value="0"/>
   <property name="message" value=""/>
   <property name="path_size_x" type="float" value="0"/>
   <property name="path_size_y" type="float" value="0"/>
   <property name="spawn" value="character"/>
   <property name="speed" type="float" value="0"/>
  </properties>
 </tile>
 <tile id="5">
  <properties>
   <property name="id" value="0x"/>
   <property name="offset_x" type="float" value="0"/>
   <property name="offset_y" type="float" value="0"/>
   <property name="spawn" value="teleporter"/>
   <property name="target_id" value="0x"/>
   <property name="target_map" value=""/>
   <property name="terget_map_path" value="world/maps/"/>
  </properties>
 </tile>
</tileset>
