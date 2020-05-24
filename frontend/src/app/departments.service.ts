import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { CourseDto } from './course.dto';
import { DepratmentDto } from './department.dto';
import { ProfessorDto } from './professor.dto';

@Injectable({
  providedIn: 'root'
})
export class DepartmentsService {
  constructor(private auth: AuthService) { }

  getDepartments(): Observable<DepratmentDto[]> {
    return this.auth.api('departments');
  }

  getDepartment(id: string): Observable<DepratmentDto> {
    return this.auth.api(`departments/${id}`);
  }

  updateDepartments(department: DepratmentDto): Observable<DepratmentDto> {
    return this.auth.api('departments/', department);
  }

  deleteDepartment(id: string) {
    return this.auth.api(`departments/${id}`, undefined, 'DELETE');
  }

  addDepartment(id: string, department: DepratmentDto) {
    return this.auth.api(`departments/${id}`, department, 'PUT');
  }

  getDepartmentProfessors(id: string): Observable<ProfessorDto[]> {
    return this.auth.api(`departments/${id}/professors`);
  }
}
