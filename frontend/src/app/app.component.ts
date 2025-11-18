import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DataService, VehicleItem } from './data.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div>
      <h1>Eco Mode Management</h1>
      
      <form [formGroup]="form">
        <div class="form-group">
          <input 
            type="text" 
            formControlName="vehicleId" 
            placeholder="Enter Vehicle ID"
            [class.error]="form.get('vehicleId')?.invalid && form.get('vehicleId')?.touched">
          <div *ngIf="form.get('vehicleId')?.invalid && form.get('vehicleId')?.touched" class="error">
            Vehicle ID is required
          </div>
        </div>
        
        <div class="button-group">
          <button type="button" (click)="activateVehicle()" [disabled]="form.invalid || loading">
            {{ loading && currentAction === 'activate' ? 'Activating...' : 'Activate' }}
          </button>
          <button type="button" (click)="deactivateVehicle()" [disabled]="form.invalid || loading">
            {{ loading && currentAction === 'deactivate' ? 'Deactivating...' : 'Deactivate' }}
          </button>
        </div>
      </form>

      <div *ngIf="message" [class]="messageType">{{ message }}</div>

      <table *ngIf="data.length > 0">
        <thead>
          <tr>
            <th>Vehicle ID</th>
            <th>Status</th>
            <th>Timestamp</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let item of data">
            <td>{{ item.vehicleId }}</td>
            <td>{{ item.status }}</td>
            <td>{{ item.timestamp | date:'short' }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  `
})
export class AppComponent implements OnInit {
  form: FormGroup;
  data: VehicleItem[] = [];
  loading = false;
  message = '';
  messageType = '';
  currentAction = '';

  constructor(
    private fb: FormBuilder,
    private dataService: DataService
  ) {
    this.form = this.fb.group({
      vehicleId: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.data = this.dataService.getData();
  }

  async activateVehicle() {
    if (this.form.valid) {
      this.loading = true;
      this.currentAction = 'activate';
      this.message = '';
      
      try {
        await this.dataService.activateVehicle(this.form.value.vehicleId);
        this.data = this.dataService.getData();
        this.message = 'Eco mode activated successfully';
        this.messageType = 'success';
        this.form.reset();
      } catch (error) {
        this.message = 'Failed to activate eco mode';
        this.messageType = 'error';
      } finally {
        this.loading = false;
        this.currentAction = '';
      }
    }
  }

  async deactivateVehicle() {
    if (this.form.valid) {
      this.loading = true;
      this.currentAction = 'deactivate';
      this.message = '';
      
      try {
        await this.dataService.deactivateVehicle(this.form.value.vehicleId);
        this.data = this.dataService.getData();
        this.message = 'Eco mode deactivated successfully';
        this.messageType = 'success';
        this.form.reset();
      } catch (error) {
        this.message = 'Failed to deactivate eco mode';
        this.messageType = 'error';
      } finally {
        this.loading = false;
        this.currentAction = '';
      }
    }
  }
}