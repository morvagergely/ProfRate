import { ProfessorDto } from './professor.dto';

export class DepratmentDto {
  id: number;
  name: string;
  averageRating: number;
  doesContainKrisa: boolean;
  professors: ProfessorDto[];
}
