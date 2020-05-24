import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { ProfessorDto } from './professor.dto';
import { CourseDto } from './course.dto';

@Injectable({
  providedIn: 'root'
})
export class ProfessorsService {
  constructor(private auth: AuthService) { }

  getProfessors(): Observable<ProfessorDto[]> {
    return this.auth.api('professors');
  }

  getProfessor(id: string): Observable<ProfessorDto> {
    return this.auth.api(`professors/${id}`);
  }

  updateProfessor(course: ProfessorDto): Observable<ProfessorDto> {
    return this.auth.api(`professors/${course.id}`, course, 'PUT');
  }

  deleteProfessor(id: string) {
    return this.auth.api(`professors/${id}`, undefined, 'DELETE');
  }

  getProfessorCourses(id: string): Observable<CourseDto[]> {
    return this.auth.api(`professors/${id}/courses`);
  }

  addProfessorCourse(id: string, course: CourseDto): Observable<ProfessorDto> {
    return this.auth.api(`professors/${id}/courses`, course);
  }

  dropCourse(id: string, course: ProfessorDto): Observable<ProfessorDto> {
    return this.auth.api(`professors/${course.id}/courses`, course, 'PUT');
  }
}
