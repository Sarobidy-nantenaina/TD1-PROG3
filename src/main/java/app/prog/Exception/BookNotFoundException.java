package app.prog.Exception;

public class BookNotFoundException extends RuntimeException{
      public BookNotFoundException(int bookId){
          super("book id "+bookId+" is not found ");
      }
}
