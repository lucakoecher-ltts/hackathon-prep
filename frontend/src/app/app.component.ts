import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DataService, DataItem } from './data.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div>
      <h1>Angular App</h1>
      
      <form [formGroup]="form" (ngSubmit)="onSubmit()">
        <div class="form-group">
          <input 
            type="text" 
            formControlName="name" 
            placeholder="Enter name"
            [class.error]="form.get('name')?.invalid && form.get('name')?.touched">
          <div *ngIf="form.get('name')?.invalid && form.get('name')?.touched" class="error">
            Name is required and must be at least 2 characters
          </div>
        </div>
        
        <button type="submit" [disabled]="form.invalid">Add Item</button>
      </form>

      <table *ngIf="data.length > 0">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let item of data">
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  `
})
export class AppComponent implements OnInit {
  form: FormGroup;
  data: DataItem[] = [];

  constructor(
    private fb: FormBuilder,
    private dataService: DataService
  ) {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]]
    });
  }

  async ngOnInit() {
    this.data = await this.dataService.fetchData();
  }

  onSubmit() {
    if (this.form.valid) {
      this.dataService.addItem(this.form.value.name);
      this.data = this.dataService.getData();
      this.form.reset();
    }
  }
}