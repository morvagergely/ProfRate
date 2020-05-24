import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MediaMatcher } from '@angular/cdk/layout';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'thesis';

  mobileQuery: MediaQueryList;

  private _mobileQueryListener: () => void;

  constructor(
    public auth: AuthService,
    private router: Router,

    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
  ) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }


  trackedSubscription?: Subscription;

  handleLogin() {
    if (this.auth.isLoggedIn) {
      this.auth.logout();
    }
    else {
      this.router.navigate(['login']);
    }
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  goto(route: string) {
    this.router.navigate([route]);
  }

}
