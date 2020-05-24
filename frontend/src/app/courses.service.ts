import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { CourseDto } from './course.dto';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {
  constructor(private auth: AuthService) { }

  getCourses(): Observable<CourseDto[]> {
    return this.auth.api('courses');
  }

  getCourse(id: string): Observable<CourseDto> {
    return this.auth.api(`courses/${id}`);
  }

  updateCourse(course: CourseDto): Observable<CourseDto> {
    return this.auth.api(`courses/${course.id}`, course, 'PUT');
  }

  deleteCourse(id: string) {
    return this.auth.api(`courses/${id}`, undefined, 'DELETE');
  }
}
