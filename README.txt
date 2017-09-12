
##pom中配置suitexmlfiles元素，同时可以使用surefire中的属性配置如:

mvn clean test -Dgroups=test1
mvn clean test -DexcludedGroups=test1
