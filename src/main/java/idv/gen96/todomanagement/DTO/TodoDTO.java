package idv.gen96.todomanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//DTO負責前端和Controller之間的資料傳遞
public class TodoDTO {
    private long id;
    private String title;
    private String description;
    private boolean completed;
}
