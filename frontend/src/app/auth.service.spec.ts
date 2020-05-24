import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AuthService, AccessToken } from './auth.service';

describe('AuthService', () => {
  let httpTestingController: HttpTestingController;
  let service: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });

    httpTestingController = TestBed.get(HttpTestingController);
    service = TestBed.inject(AuthService);
  });

  afterEach(() => httpTestingController.verify());

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('#register()', () => {
    it('returned Observable should emit undefined', () => {
      service.register('foo', 'bar').subscribe(result => {
        expect(result).toBeUndefined();
      });

      const req = httpTestingController.expectOne(`${service.base}auth/register`);

      expect(req.request.method).toEqual('POST');

      req.flush({});
    });
  });

  describe('#login()', () => {
    it('should work', () => {
      service.login('foo', 'bar').subscribe(result => {
        expect(service.token).toBe('foobar');
        expect(localStorage.getItem('token')).toBe('foobar');
        expect(result).toBeUndefined();
      });

      const req = httpTestingController.expectOne(`${service.base}auth/login`);

      expect(req.request.method).toEqual('POST');

      req.flush({ access_token: 'foobar' } as AccessToken);
    });
  });

  describe('#logout()', () => {
    it('should remove token', () => {
      service.logout();

      expect(service.token).toBeUndefined();
      expect(localStorage.getItem('token')).toBeNull();
    });
  });
});
