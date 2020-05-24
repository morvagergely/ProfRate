import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { CoursesService } from '../courses.service';
import { tap, mapTo } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { CourseDto } from '../course.dto';

@Component({
  selector: 'app-professors',
  templateUrl: './professors.component.html',
  styleUrls: ['./professors.component.scss']
})
export class ProfessorsComponent implements OnInit, OnDestroy {
  public readonly pageSize = 20;
  public currentPage = 0;

  filterSubscription?: Subscription;

  constructor(
    private service: CoursesService,
    public auth: AuthService,
    private router: Router,
  ) {
  }

  arrayOne(n: number): any[] {
    return Array(n);
  }

  selected = '5';
  courses: CourseDto[];

  ngOnInit(): void {
    this.updateProfessors().subscribe();
  }

  ngOnDestroy(): void {
    this.filterSubscription?.unsubscribe();
  }

  goto(path: string, additional: string) {
    console.log(`${path}/${additional}`);
    this.router.navigate([`${path}/${additional}`]);
  }


  private updateProfessors(): Observable<null> {
    return this.service.getCourses().pipe(
      tap(courses => {
        this.courses = courses;
      }),
      mapTo(null),
    );
  }
}
