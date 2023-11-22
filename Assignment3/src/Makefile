<<<<<<< HEAD
# Just a makefile to save some typing :-) 
# Serguei A. Mokhov, mokhov@cs.concordia.ca  
# PA3
# Use gmake. 

JAVAC=javac
JFLAGS=-g
JVM=java
EXE=DiningPhilosophers

ASMTDIRS := . common

# Lists of all *.java and *.class files
JAVAFILES := $(ASMTDIRS:%=%/*.java)
CLASSES := $(ASMTDIRS:%=%/*.class)

all: $(EXE).class 
	@echo "Dining Philosophers Application has been built."

$(EXE).class: $(JAVAFILES)
	$(JAVAC) $(JFLAGS) $(EXE).java

run: all
	$(JVM) $(EXE)

regression: all
	@for arg in 3 4 5; do $(JVM) $(EXE) $$arg; done

clean:
	rm -f $(CLASSES) #* *~

# EOF
=======
# Just a makefile to save some typing :-) 
# Serguei A. Mokhov, mokhov@cs.concordia.ca  
# PA3
# Use gmake. 

JAVAC=javac
JFLAGS=-g
JVM=java
EXE=DiningPhilosophers

ASMTDIRS := . common

# Lists of all *.java and *.class files
JAVAFILES := $(ASMTDIRS:%=%/*.java)
CLASSES := $(ASMTDIRS:%=%/*.class)

all: $(EXE).class 
	@echo "Dining Philosophers Application has been built."

$(EXE).class: $(JAVAFILES)
	$(JAVAC) $(JFLAGS) $(EXE).java

run: all
	$(JVM) $(EXE)

regression: all
	@for arg in 3 4 5; do $(JVM) $(EXE) $$arg; done

clean:
	rm -f $(CLASSES) #* *~

# EOF
>>>>>>> 5c4205167ecfd8f58f91310168960a5aeb150765
