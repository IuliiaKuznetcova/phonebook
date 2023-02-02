package api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class ContactDto {

    String firstName;
    String lastName;
    String description;


}
