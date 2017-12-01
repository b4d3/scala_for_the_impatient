package traits

/**
  * 7. Construct an example where a class needs to be recompiled when one of the mixins changes. Start with class SavingsAccount extends Account with ConsoleLogger. Put each class and trait in a separate source file. Add a field to Account. In Main (also in a separate source file), construct a SavingsAccount and access the new field. Recompile all files except for SavingsAccount and verify that the program works. Now add a field to ConsoleLogger and access it in Main. Again, recompile all files except for SavingsAccount. What happens? Why?
  */
class SavingsAccount extends Account with ConsoleLogger