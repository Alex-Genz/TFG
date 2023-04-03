<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.0" name="forest_tiles" tilewidth="32" tileheight="32" tilecount="1408" columns="44">
 <image source="../img/pngkit_tile-png_2654326.png" width="1408" height="1024"/>
 <tile id="220" probability="0.1"/>
 <tile id="221" probability="0.1"/>
 <tile id="222" probability="0.1"/>
 <tile id="223" probability="0.1"/>
 <tile id="224" probability="0.1"/>
 <tile id="225" probability="0.1"/>
 <tile id="226" probability="0.1"/>
 <tile id="227" probability="0.1"/>
 <tile id="228" probability="0.1"/>
 <tile id="229" probability="0.1"/>
 <tile id="230" probability="0.1"/>
 <tile id="231" probability="0.1"/>
 <tile id="232" probability="0.1"/>
 <tile id="233" probability="0.1"/>
 <tile id="234" probability="0.1"/>
 <tile id="484" probability="0.1">
  <animation>
   <frame tileid="397" duration="600"/>
   <frame tileid="485" duration="600"/>
   <frame tileid="484" duration="600"/>
   <frame tileid="486" duration="600"/>
   <frame tileid="484" duration="600"/>
   <frame tileid="485" duration="600"/>
   <frame tileid="397" duration="600"/>
  </animation>
 </tile>
 <tile id="485" probability="0.1"/>
 <tile id="486" probability="0.1"/>
 <tile id="487" probability="0.3">
  <animation>
   <frame tileid="400" duration="600"/>
   <frame tileid="488" duration="600"/>
   <frame tileid="487" duration="600"/>
   <frame tileid="489" duration="600"/>
   <frame tileid="487" duration="600"/>
   <frame tileid="488" duration="600"/>
   <frame tileid="400" duration="600"/>
  </animation>
 </tile>
 <tile id="488" probability="0.3"/>
 <tile id="489" probability="0.3"/>
 <tile id="490" probability="0.3">
  <animation>
   <frame tileid="403" duration="600"/>
   <frame tileid="491" duration="600"/>
   <frame tileid="490" duration="600"/>
   <frame tileid="492" duration="600"/>
   <frame tileid="490" duration="600"/>
   <frame tileid="491" duration="600"/>
   <frame tileid="403" duration="600"/>
  </animation>
 </tile>
 <tile id="748" probability="0.037"/>
 <tile id="749" probability="0.037"/>
 <tile id="750" probability="0.037"/>
 <tile id="751" probability="0.037"/>
 <tile id="752" probability="0.037"/>
 <tile id="753" probability="0.037"/>
 <tile id="1044">
  <properties>
   <property name="tree_type" value="tree1"/>
  </properties>
 </tile>
 <tile id="1045">
  <properties>
   <property name="tree_type" value="tree2"/>
  </properties>
 </tile>
 <tile id="1264">
  <properties>
   <property name="tree_type" value="tree3"/>
  </properties>
 </tile>
 <tile id="1265">
  <properties>
   <property name="tree_type" value="tree4"/>
  </properties>
 </tile>
 <wangsets>
  <wangset name="grass" type="corner" tile="-1">
   <wangcolor name="grass" color="#ff0000" tile="-1" probability="1"/>
   <wangtile tileid="529" wangid="0,1,0,0,0,1,0,1"/>
   <wangtile tileid="530" wangid="0,1,0,1,0,0,0,1"/>
   <wangtile tileid="573" wangid="0,0,0,1,0,1,0,1"/>
   <wangtile tileid="574" wangid="0,1,0,1,0,1,0,0"/>
   <wangtile tileid="616" wangid="0,0,0,1,0,0,0,0"/>
   <wangtile tileid="617" wangid="0,0,0,1,0,1,0,0"/>
   <wangtile tileid="618" wangid="0,0,0,0,0,1,0,0"/>
   <wangtile tileid="660" wangid="0,1,0,1,0,0,0,0"/>
   <wangtile tileid="661" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="662" wangid="0,0,0,0,0,1,0,1"/>
   <wangtile tileid="704" wangid="0,1,0,0,0,0,0,0"/>
   <wangtile tileid="705" wangid="0,1,0,0,0,0,0,1"/>
   <wangtile tileid="706" wangid="0,0,0,0,0,0,0,1"/>
   <wangtile tileid="748" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="749" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="750" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="751" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="752" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="753" wangid="0,1,0,1,0,1,0,1"/>
  </wangset>
  <wangset name="water" type="corner" tile="-1">
   <wangcolor name="water_dirt" color="#ff0000" tile="-1" probability="1"/>
   <wangcolor name="water_dirt_frosty" color="#00ff00" tile="-1" probability="1"/>
   <wangcolor name="snow_island_frosty" color="#0000ff" tile="-1" probability="1"/>
   <wangcolor name="water_snow" color="#ff7700" tile="-1" probability="1"/>
   <wangcolor name="water_sand" color="#00e9ff" tile="-1" probability="1"/>
   <wangtile tileid="265" wangid="0,1,0,0,0,1,0,1"/>
   <wangtile tileid="266" wangid="0,1,0,1,0,0,0,1"/>
   <wangtile tileid="271" wangid="0,2,0,0,0,2,0,2"/>
   <wangtile tileid="272" wangid="0,2,0,2,0,0,0,2"/>
   <wangtile tileid="274" wangid="0,3,0,2,0,3,0,3"/>
   <wangtile tileid="275" wangid="0,3,0,3,0,2,0,3"/>
   <wangtile tileid="277" wangid="0,4,0,1,0,4,0,4"/>
   <wangtile tileid="278" wangid="0,4,0,4,0,1,0,4"/>
   <wangtile tileid="280" wangid="0,5,0,1,0,5,0,5"/>
   <wangtile tileid="281" wangid="0,5,0,5,0,1,0,5"/>
   <wangtile tileid="309" wangid="0,0,0,1,0,1,0,1"/>
   <wangtile tileid="310" wangid="0,1,0,1,0,1,0,0"/>
   <wangtile tileid="315" wangid="0,0,0,2,0,2,0,2"/>
   <wangtile tileid="316" wangid="0,2,0,2,0,2,0,0"/>
   <wangtile tileid="318" wangid="0,2,0,3,0,3,0,3"/>
   <wangtile tileid="319" wangid="0,3,0,3,0,3,0,2"/>
   <wangtile tileid="321" wangid="0,1,0,4,0,4,0,4"/>
   <wangtile tileid="322" wangid="0,4,0,4,0,4,0,1"/>
   <wangtile tileid="324" wangid="0,1,0,5,0,5,0,5"/>
   <wangtile tileid="325" wangid="0,5,0,5,0,5,0,1"/>
   <wangtile tileid="352" wangid="0,0,0,1,0,0,0,0"/>
   <wangtile tileid="353" wangid="0,0,0,1,0,1,0,0"/>
   <wangtile tileid="354" wangid="0,0,0,0,0,1,0,0"/>
   <wangtile tileid="358" wangid="0,0,0,2,0,0,0,0"/>
   <wangtile tileid="359" wangid="0,0,0,2,0,2,0,0"/>
   <wangtile tileid="360" wangid="0,0,0,0,0,2,0,0"/>
   <wangtile tileid="361" wangid="0,2,0,3,0,2,0,2"/>
   <wangtile tileid="362" wangid="0,2,0,3,0,3,0,2"/>
   <wangtile tileid="363" wangid="0,2,0,2,0,3,0,2"/>
   <wangtile tileid="364" wangid="0,1,0,4,0,1,0,1"/>
   <wangtile tileid="365" wangid="0,1,0,4,0,4,0,1"/>
   <wangtile tileid="366" wangid="0,1,0,1,0,4,0,1"/>
   <wangtile tileid="367" wangid="0,1,0,5,0,1,0,1"/>
   <wangtile tileid="368" wangid="0,1,0,5,0,5,0,1"/>
   <wangtile tileid="369" wangid="0,1,0,1,0,5,0,1"/>
   <wangtile tileid="396" wangid="0,1,0,1,0,0,0,0"/>
   <wangtile tileid="397" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="398" wangid="0,0,0,0,0,1,0,1"/>
   <wangtile tileid="402" wangid="0,2,0,2,0,0,0,0"/>
   <wangtile tileid="403" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="404" wangid="0,0,0,0,0,2,0,2"/>
   <wangtile tileid="405" wangid="0,3,0,3,0,2,0,2"/>
   <wangtile tileid="406" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="407" wangid="0,2,0,2,0,3,0,3"/>
   <wangtile tileid="408" wangid="0,4,0,4,0,1,0,1"/>
   <wangtile tileid="409" wangid="0,4,0,4,0,4,0,4"/>
   <wangtile tileid="410" wangid="0,1,0,1,0,4,0,4"/>
   <wangtile tileid="411" wangid="0,5,0,5,0,1,0,1"/>
   <wangtile tileid="412" wangid="0,5,0,5,0,5,0,5"/>
   <wangtile tileid="413" wangid="0,1,0,1,0,5,0,5"/>
   <wangtile tileid="440" wangid="0,1,0,0,0,0,0,0"/>
   <wangtile tileid="441" wangid="0,1,0,0,0,0,0,1"/>
   <wangtile tileid="442" wangid="0,0,0,0,0,0,0,1"/>
   <wangtile tileid="446" wangid="0,2,0,0,0,0,0,0"/>
   <wangtile tileid="447" wangid="0,2,0,0,0,0,0,2"/>
   <wangtile tileid="448" wangid="0,0,0,0,0,0,0,2"/>
   <wangtile tileid="449" wangid="0,3,0,2,0,2,0,2"/>
   <wangtile tileid="450" wangid="0,3,0,2,0,2,0,3"/>
   <wangtile tileid="451" wangid="0,2,0,2,0,2,0,3"/>
   <wangtile tileid="452" wangid="0,4,0,1,0,1,0,1"/>
   <wangtile tileid="453" wangid="0,4,0,1,0,1,0,4"/>
   <wangtile tileid="454" wangid="0,1,0,1,0,1,0,4"/>
   <wangtile tileid="455" wangid="0,5,0,1,0,1,0,1"/>
   <wangtile tileid="456" wangid="0,5,0,1,0,1,0,5"/>
   <wangtile tileid="457" wangid="0,1,0,1,0,1,0,5"/>
   <wangtile tileid="484" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="490" wangid="0,2,0,2,0,2,0,2"/>
  </wangset>
  <wangset name="dirt" type="corner" tile="-1">
   <wangcolor name="dirt1" color="#ff0000" tile="-1" probability="1"/>
   <wangcolor name="dirt2" color="#00ff00" tile="-1" probability="1"/>
   <wangcolor name="dirt3" color="#0000ff" tile="-1" probability="1"/>
   <wangcolor name="basalt" color="#ff7700" tile="-1" probability="1"/>
   <wangcolor name="stone" color="#00e9ff" tile="-1" probability="1"/>
   <wangtile tileid="1" wangid="0,1,0,0,0,1,0,1"/>
   <wangtile tileid="2" wangid="0,1,0,1,0,0,0,1"/>
   <wangtile tileid="4" wangid="0,2,0,0,0,2,0,2"/>
   <wangtile tileid="5" wangid="0,2,0,2,0,0,0,2"/>
   <wangtile tileid="7" wangid="0,3,0,0,0,3,0,3"/>
   <wangtile tileid="8" wangid="0,3,0,3,0,0,0,3"/>
   <wangtile tileid="10" wangid="0,4,0,0,0,4,0,4"/>
   <wangtile tileid="11" wangid="0,4,0,4,0,0,0,4"/>
   <wangtile tileid="13" wangid="0,5,0,0,0,5,0,5"/>
   <wangtile tileid="14" wangid="0,5,0,5,0,0,0,5"/>
   <wangtile tileid="45" wangid="0,0,0,1,0,1,0,1"/>
   <wangtile tileid="46" wangid="0,1,0,1,0,1,0,0"/>
   <wangtile tileid="48" wangid="0,0,0,2,0,2,0,2"/>
   <wangtile tileid="49" wangid="0,2,0,2,0,2,0,0"/>
   <wangtile tileid="51" wangid="0,0,0,3,0,3,0,3"/>
   <wangtile tileid="52" wangid="0,3,0,3,0,3,0,0"/>
   <wangtile tileid="54" wangid="0,0,0,4,0,4,0,4"/>
   <wangtile tileid="55" wangid="0,4,0,4,0,4,0,0"/>
   <wangtile tileid="57" wangid="0,0,0,5,0,5,0,5"/>
   <wangtile tileid="58" wangid="0,5,0,5,0,5,0,0"/>
   <wangtile tileid="88" wangid="0,0,0,1,0,0,0,0"/>
   <wangtile tileid="89" wangid="0,0,0,1,0,1,0,0"/>
   <wangtile tileid="90" wangid="0,0,0,0,0,1,0,0"/>
   <wangtile tileid="91" wangid="0,0,0,2,0,0,0,0"/>
   <wangtile tileid="92" wangid="0,0,0,2,0,2,0,0"/>
   <wangtile tileid="93" wangid="0,0,0,0,0,2,0,0"/>
   <wangtile tileid="94" wangid="0,0,0,3,0,0,0,0"/>
   <wangtile tileid="95" wangid="0,0,0,3,0,3,0,0"/>
   <wangtile tileid="96" wangid="0,0,0,0,0,3,0,0"/>
   <wangtile tileid="97" wangid="0,0,0,4,0,0,0,0"/>
   <wangtile tileid="98" wangid="0,0,0,4,0,4,0,0"/>
   <wangtile tileid="99" wangid="0,0,0,0,0,4,0,0"/>
   <wangtile tileid="100" wangid="0,0,0,5,0,0,0,0"/>
   <wangtile tileid="101" wangid="0,0,0,5,0,5,0,0"/>
   <wangtile tileid="102" wangid="0,0,0,0,0,5,0,0"/>
   <wangtile tileid="132" wangid="0,1,0,1,0,0,0,0"/>
   <wangtile tileid="133" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="134" wangid="0,0,0,0,0,1,0,1"/>
   <wangtile tileid="135" wangid="0,2,0,2,0,0,0,0"/>
   <wangtile tileid="136" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="137" wangid="0,0,0,0,0,2,0,2"/>
   <wangtile tileid="138" wangid="0,3,0,3,0,0,0,0"/>
   <wangtile tileid="139" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="140" wangid="0,0,0,0,0,3,0,3"/>
   <wangtile tileid="141" wangid="0,4,0,4,0,0,0,0"/>
   <wangtile tileid="142" wangid="0,4,0,4,0,4,0,4"/>
   <wangtile tileid="143" wangid="0,0,0,0,0,4,0,4"/>
   <wangtile tileid="144" wangid="0,5,0,5,0,0,0,0"/>
   <wangtile tileid="145" wangid="0,5,0,5,0,5,0,5"/>
   <wangtile tileid="146" wangid="0,0,0,0,0,5,0,5"/>
   <wangtile tileid="176" wangid="0,1,0,0,0,0,0,0"/>
   <wangtile tileid="177" wangid="0,1,0,0,0,0,0,1"/>
   <wangtile tileid="178" wangid="0,0,0,0,0,0,0,1"/>
   <wangtile tileid="179" wangid="0,2,0,0,0,0,0,0"/>
   <wangtile tileid="180" wangid="0,2,0,0,0,0,0,2"/>
   <wangtile tileid="181" wangid="0,0,0,0,0,0,0,2"/>
   <wangtile tileid="182" wangid="0,3,0,0,0,0,0,0"/>
   <wangtile tileid="183" wangid="0,3,0,0,0,0,0,3"/>
   <wangtile tileid="184" wangid="0,0,0,0,0,0,0,3"/>
   <wangtile tileid="185" wangid="0,4,0,0,0,0,0,0"/>
   <wangtile tileid="186" wangid="0,4,0,0,0,0,0,4"/>
   <wangtile tileid="187" wangid="0,0,0,0,0,0,0,4"/>
   <wangtile tileid="188" wangid="0,5,0,0,0,0,0,0"/>
   <wangtile tileid="189" wangid="0,5,0,0,0,0,0,5"/>
   <wangtile tileid="190" wangid="0,0,0,0,0,0,0,5"/>
   <wangtile tileid="220" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="221" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="222" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="223" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="224" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="225" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="226" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="227" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="228" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="229" wangid="0,4,0,4,0,4,0,4"/>
   <wangtile tileid="230" wangid="0,4,0,4,0,4,0,4"/>
   <wangtile tileid="231" wangid="0,4,0,4,0,4,0,4"/>
   <wangtile tileid="232" wangid="0,5,0,5,0,5,0,5"/>
   <wangtile tileid="233" wangid="0,5,0,5,0,5,0,5"/>
   <wangtile tileid="234" wangid="0,5,0,5,0,5,0,5"/>
  </wangset>
 </wangsets>
</tileset>
