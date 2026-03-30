JAVAC = javac
JAVA = java
JAR = jar
SRC_DIR = src
BUILD_DIR = build
LIB_DIR = lib
LIB_JAR = $(LIB_DIR)/mariadb-java-client-3.5.6.jar
MAIN_CLASS = Papillon
JAR_NAME = Papillon.jar
MANIFEST = MANIFEST.MF

# Détection de l'OS
ifeq ($(OS),Windows_NT)
    MKDIR = if not exist $(BUILD_DIR) mkdir $(BUILD_DIR)
    RM_CLASS = del /Q $(BUILD_DIR)\*.class
    RM_JAR = del /Q $(JAR_NAME)
    CLASSPATH = $(BUILD_DIR);$(LIB_JAR)
else
    MKDIR = mkdir -p $(BUILD_DIR)
    RM_CLASS = rm -f $(BUILD_DIR)/*.class
    RM_JAR = rm -f $(JAR_NAME)
    CLASSPATH = $(BUILD_DIR):$(LIB_JAR)
endif

# Cible par défaut
all: run

# Compilation des classes
compile:
	$(MKDIR)
	$(JAVAC) -d $(BUILD_DIR) -cp $(CLASSPATH) $(SRC_DIR)/*.java

# Création du JAR exécutable
$(JAR_NAME): compile $(MANIFEST)
	$(RM_JAR)
	$(JAR) cfm $(JAR_NAME) $(MANIFEST) -C $(BUILD_DIR) .

# Exécution du programme via le JAR
run: $(JAR_NAME)
	$(JAVA) -jar $(JAR_NAME)

# Nettoyage
clean:
	$(RM_CLASS)
	$(RM_JAR)

.PHONY: all compile run clean
