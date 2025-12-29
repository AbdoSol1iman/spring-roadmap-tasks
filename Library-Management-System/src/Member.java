import java.util.*;

public class Member {
    private String name;
    private String memberId;
    private List<book> borrowedBooks = new ArrayList<>();

    Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }

    String getMemberId() {
        return memberId;
    }
    void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    String GetInfo(){
        return this.name + " \"" + this.memberId+ "\".";
    }

    void BorrowBook(book book1) {
        if(this.borrowedBooks.contains(book1)) {
            System.out.println("Book already borrowed");
        }else{
        this.borrowedBooks.add(book1);
      //  book1.borrow(); هنا مينفعش هوا اللي يستلف دي من خصائص المكتبه


        }
    }
    void ReturnBook(book book1) {
        this.borrowedBooks.remove(book1);
        System.out.println( book1.GetInfo()+" Has been returned.");
    }


}
